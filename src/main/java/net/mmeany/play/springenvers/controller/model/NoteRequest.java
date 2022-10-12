package net.mmeany.play.springenvers.controller.model;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NoteRequest {

    @Parameter(description = "The notes title")
    @NotBlank
    @Size(min = 3, message = "Must be at least 3 characters")
    private String title;

    @Parameter(description = "The notes content")
    @NotBlank
    @Size(min = 3, message = "Must be at least 3 characters")
    private String description;
}
