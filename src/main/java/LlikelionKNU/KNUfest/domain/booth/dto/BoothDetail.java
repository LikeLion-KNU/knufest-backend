package LlikelionKNU.KNUfest.domain.booth.dto;

import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoothDetail {
    private String boothName;
    private String boothDescription;
    private int likes;
    private String categori;
    private int boothnum;
    private List<String> urls;
    private boolean Likable;

}
