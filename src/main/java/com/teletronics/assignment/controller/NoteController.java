package com.teletronics.assignment.controller;

import com.teletronics.assignment.model.Tag;
import com.teletronics.assignment.model.CreateNoteRequest;
import com.teletronics.assignment.model.Note;
import com.teletronics.assignment.model.NoteListResponse;
import com.teletronics.assignment.model.UpdateNoteRequest;
import com.teletronics.assignment.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class NoteController {

    @Autowired
    private NoteServiceImpl noteService;

    @GetMapping("/note/{id}")
    public Note getNote(@PathVariable UUID id) {
        return noteService
            .findNote(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "entity not found"));
    }

    @GetMapping("/note")
    public List<NoteListResponse> getNotes(@RequestParam(value = "tag") Optional<Tag> tag,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "20") int size) {
        final var pageable = PageRequest.of(page, size, DESC, "createdDate");
        return noteService
            .listNotes(pageable, tag)
            .stream()
            .map(NoteListResponse::fromNote)
            .toList();
    }

    @GetMapping("/note/{id}/stats")
    public Map<String, Integer> getStats(@PathVariable UUID id) {
        return noteService.getStats(id);
    }

    @PutMapping("/note")
    public Note createNote(@RequestBody CreateNoteRequest request) {
        return noteService.createNote(request.toNote());
    }

    @PostMapping("/note/{id}")
    public void updateNote(@PathVariable UUID id, @RequestBody UpdateNoteRequest request) {
        noteService.updateNote(id, request);
    }

    @DeleteMapping("/note/{id}")
    public void deleteNote(@PathVariable UUID id) {
        noteService.deleteNote(id);
    }
}
