package com.tlt.test.service;

import com.tlt.test.model.Note;
import com.tlt.test.model.NoteDocument;
import com.tlt.test.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class NoteServiceTest {

    @TestConfiguration
    static class NoteServiceImplTestContextConfiguration {

        @Bean
        public NoteService noteService() {
            return new NoteServiceImpl();
        }
    }

    @MockBean
    NoteRepository repository;
    @Autowired
    NoteServiceImpl service;

    @Test
    void savesNote() {
        var note = new Note(randomUUID(), "", LocalDate.now(), "", Optional.empty());
        var document = NoteDocument.fromNote(note);
        given(repository.save(document)).willReturn(document);
        var result = service.createNote(note);
        assertThat(result).isEqualTo(note);
    }

    @Test
    void getsStats() {
        var note = new Note(randomUUID(), "title", LocalDate.now(), "this is a note", Optional.empty());
        var document = NoteDocument.fromNote(note);
        given(repository.findById(note.id())).willReturn(Optional.of(document));
        var result = service.getStats(note.id());
        assertThat(result).isEqualTo(Map.of("this", 1, "is", 1, "a", 1, "note", 1));
    }
}