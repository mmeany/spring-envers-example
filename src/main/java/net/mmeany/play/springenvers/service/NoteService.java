package net.mmeany.play.springenvers.service;

import net.mmeany.play.springenvers.model.Note;
import net.mmeany.play.springenvers.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional()
    public Note create(String title, String description) {
        return noteRepository.save(Note.builder().title(title).description(description).build());
    }

    public List<Note> notes() {
        return noteRepository.findAll();
    }
}
