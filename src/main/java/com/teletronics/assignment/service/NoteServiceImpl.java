package com.teletronics.assignment.service;

import com.teletronics.assignment.model.Note;
import com.teletronics.assignment.model.Tag;
import com.teletronics.assignment.model.NoteDocument;
import com.teletronics.assignment.model.UpdateNoteRequest;
import com.teletronics.assignment.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.stream;
import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Optional<Note> findNote(UUID id) {
        return noteRepository
            .findById(id)
            .map(NoteDocument::toNote);
    }

    public Note createNote(Note note) {
        return noteRepository.save(NoteDocument.fromNote(note)).toNote();
    }

    public void updateNote(UUID id, UpdateNoteRequest request) {
        noteRepository
            .findById(id)
            .map(noteDocument -> new Note(noteDocument.id(), request.title(), noteDocument.createdDate(), request.text(), request.tag()))
            .map(NoteDocument::fromNote)
            .map(noteRepository::save);
    }

    public void deleteNote(UUID id) {
        noteRepository.deleteById(id);
    }

    public List<Note> listNotes(PageRequest pageRequest, Optional<Tag> tag) {
        final var result = tag.isEmpty() ? noteRepository.findAll(pageRequest) : noteRepository.findAllByTag(tag.get(), pageRequest);
        return result
            .getContent()
            .stream()
            .map(NoteDocument::toNote)
            .filter(note -> tag.isEmpty() || note.tag().equals(tag))
            .toList();
    }

    public Map<String, Integer> getStats(UUID id) {
        return noteRepository
            .findById(id)
            .map(note -> stream(note.text().split(" ")).toList())
            .map(list -> list.stream().collect(groupingBy(identity())).entrySet().stream().collect(toMap(Map.Entry::getKey, entry -> entry.getValue().size())))
            .orElse(emptyMap());
    }
}
