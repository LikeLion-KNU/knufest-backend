package LlikelionKNU.KNUfest.domain.booth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BoothDetailDto {
    int id;
    String name;
    int likes;
    int views;
    List<CommentDto> comments;
}
