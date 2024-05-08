package LlikelionKNU.KNUfest.domain.comment.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentRequest {
    String name;
    String comment;
    String password;
}
