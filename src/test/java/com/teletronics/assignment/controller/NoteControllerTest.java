package com.teletronics.assignment.controller;

import com.teletronics.assignment.model.Note;
import com.teletronics.assignment.repository.NoteRepository;
import com.teletronics.assignment.model.NoteDocument;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteRepository repository;


    @Test
    void returnsNote() throws Exception {
        var note = new Note(UUID.randomUUID(), "bob", LocalDate.now(), "text", Optional.empty());
        given(repository.findById(note.id())).willReturn(Optional.of(NoteDocument.fromNote(note)));

        mvc.perform(get("/note/" + note.id()).contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", is("bob")));
    }

    @Test
    void returnsNotes() throws Exception {
        var note = new Note(UUID.randomUUID(), "bob", LocalDate.now(), "text", Optional.empty());
        given(repository.findAll(any(PageRequest.class))).willReturn(new PageImpl(List.of(NoteDocument.fromNote(note))));

        mvc.perform(get("/note").contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]title", is("bob")));
    }
}