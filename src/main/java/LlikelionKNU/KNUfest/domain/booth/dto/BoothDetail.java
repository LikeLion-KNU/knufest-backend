package LlikelionKNU.KNUfest.domain.booth.dto;

import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoothDetail {
    private Long id;
    private String boothName;
    private int likes;
    private String categori;
    private int boothnum;
    private List<String> urls;
    private boolean Likable;

    private List<Comment> comments;
}
