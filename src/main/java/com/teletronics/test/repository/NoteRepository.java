package com.teletronics.test.repository;

import com.teletronics.test.model.Tag;
import com.teletronics.test.model.NoteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepository extends MongoRepository<NoteDocument, UUID> {

    Page<NoteDocument> findAllByTag(Tag tag, Pageable pageable);
}