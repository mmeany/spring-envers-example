package net.mmeany.play.springenvers.repository;

import net.mmeany.play.springenvers.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
