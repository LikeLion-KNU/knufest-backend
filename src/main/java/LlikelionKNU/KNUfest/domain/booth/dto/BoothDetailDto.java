package LlikelionKNU.KNUfest.domain.booth.dto;

import LlikelionKNU.KNUfest.domain.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class BoothDetailDto {
    int id;
    int likes;
    List<CommentDto> comments;
}
