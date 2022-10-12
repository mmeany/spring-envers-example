package net.mmeany.play.springenvers.controller.model;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NoteRequest {
    private String title;
    private String description;
}
