package com.teletronics.assignment.model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

public record Note(UUID id, String title, LocalDate createdDate, String text, Optional<Tag> tag) {
    public Note(UUID id, String title, LocalDate createdDate, String text, Optional<Tag> tag) {
        this.id = checkNotNull("id", id);
        this.title = checkNotEmpty("title", title);
        this.createdDate = checkNotNull("createdDate", createdDate);
        this.text = checkNotEmpty("text", text);
        this.tag = checkNotNull("tag", tag);
    }

    private <T> T checkNotNull(String field, T value) {
        if (value == null) {
            throw new IllegalArgumentException(format("Provided value for field %s is null", field));
        }
        return value;
    }

    private String checkNotEmpty(String field, String value) {
        checkNotNull(field, value);
        if (value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException(format("Provided value for field %s is empty", field));
        }
        return value;
    }
}
