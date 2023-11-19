package com.teletronics.assignment.model.dao;

import com.teletronics.assignment.model.Tag;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.UUID.randomUUID;

public record UpdateNoteRequest(String title, LocalDate createdDate, String text, Optional<Tag> tag) {

}
