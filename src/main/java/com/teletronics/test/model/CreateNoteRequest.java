package com.teletronics.test.model;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.UUID.randomUUID;

public record CreateNoteRequest(String title, String text, Optional<Tag> tag) {

    public Note toNote() {
        return new Note(randomUUID(), title, LocalDate.now(), text, tag);
    }
}
