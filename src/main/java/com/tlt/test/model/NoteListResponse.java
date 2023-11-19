package com.tlt.test.model;

import java.time.LocalDate;
import java.util.UUID;

public record NoteListResponse(UUID id, String title, LocalDate createdDate) {

    public static NoteListResponse fromNote(Note note) {
        return new NoteListResponse(note.id(), note.title(), note.createdDate());
    }
}
