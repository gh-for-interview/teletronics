package com.teletronics.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Document(collection="notes")
public record NoteDocument(@Id UUID id, String title, LocalDate createdDate, String text, Tag tag) {

    public Note toNote() {
        return new Note(id, title, createdDate, text, ofNullable(tag));
    }

    public static NoteDocument fromNote(Note note) {
        return new NoteDocument(note.id(), note.title(), note.createdDate(), note.text(), note.tag().orElse(null));
    }
}
