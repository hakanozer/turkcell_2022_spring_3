package com.works.repositories;

import com.works.entities.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // List<Note> findByAidEquals(Long aid);

    Page<Note> findByAidEquals(Long aid, Pageable pageable);



    Optional<Note> findByNidEquals(Long nid);



}
