package net.mmeany.play.springenvers.controller;

import net.mmeany.play.springenvers.controller.model.NoteRequest;
import net.mmeany.play.springenvers.controller.model.NoteResponse;
import net.mmeany.play.springenvers.model.Note;
import net.mmeany.play.springenvers.service.NoteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Note>> allNotes() {
        return ResponseEntity.ok(noteService.notes());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteResponse> save(@RequestBody NoteRequest noteRequest) {
        return ResponseEntity.ok(
                NoteResponse.builder()
                        .id(noteService.create(noteRequest.getTitle(), noteRequest.getDescription()).getId())
                        .build()
        );
    }
}
