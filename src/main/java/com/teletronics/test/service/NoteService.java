package com.teletronics.test.service;

import com.teletronics.test.model.Note;
import com.teletronics.test.model.Tag;
import com.teletronics.test.model.UpdateNoteRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface NoteService {

    Optional<Note> findNote(UUID id);
    Note createNote(Note note);
    void updateNote(UUID id, UpdateNoteRequest request);
    void deleteNote(UUID id);
    List<Note> listNotes(PageRequest pageRequest, Optional<Tag> tag);
    Map<String, Integer> getStats(UUID id);
}
