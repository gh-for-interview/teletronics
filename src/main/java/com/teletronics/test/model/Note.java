package com.teletronics.test.model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

public record Note(UUID id, String title, LocalDate createdDate, String text, Optional<Tag> tag) {
    public Note(UUID id, String title, LocalDate createdDate, String text, Optional<Tag> tag) {
        this.id = checkNotNull("id", id);
        this.title = checkNotNull("title", title);
        this.createdDate = checkNotNull("createdDate", createdDate);
        this.text = checkNotNull("text", text);
        this.tag = checkNotNull("tag", tag);
    }

    private <T> T checkNotNull(String field, T value) {
        if (value == null) {
            throw new IllegalArgumentException(format("Provided value for field %s is null", field));
        }
        return value;
    }
}
