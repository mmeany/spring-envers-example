package net.mmeany.play.springenvers.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import net.mmeany.play.springenvers.controller.model.NoteDto;
import net.mmeany.play.springenvers.controller.model.NoteRequest;
import net.mmeany.play.springenvers.service.NoteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/note")
@Validated
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "Fetch all notes")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteDto>> allNotes() {
        return ResponseEntity.ok(noteService.notes());
    }

    @Operation(summary = "Create a new note")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> save(@Valid @RequestBody NoteRequest noteRequest) {
        return ResponseEntity.ok(
                noteService.create(noteRequest.getTitle(), noteRequest.getDescription())
        );
    }

    @Operation(summary = "Fetch a specific note by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> note(@Parameter(description = "Note id") @Positive @PathVariable("id") Long id) {
        return ResponseEntity.of(noteService.note(id));
    }
}
