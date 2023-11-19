package com.tlt.test.model;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.UUID.randomUUID;

public record UpdateNoteRequest(String title, LocalDate createdDate, String text, Optional<Tag> tag) {

    public Note toNote() {
        return new Note(randomUUID(), title, createdDate, text, tag);
    }
}
