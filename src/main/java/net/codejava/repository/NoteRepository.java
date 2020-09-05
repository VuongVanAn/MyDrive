package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
