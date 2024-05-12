package LlikelionKNU.KNUfest.domain.booth.dto;

import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoothDetail {
    Long id;
    String boothName;
    int likes;
    List<String> urls;
    boolean Likable;

    List<Comment> comments;
}
