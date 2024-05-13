package LlikelionKNU.KNUfest.domain.comment.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentRequest {
    private String name;
    private String comment;
}
