package LlikelionKNU.KNUfest.domain.booth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Booth {
    private Long id;
    private String boothName;
    private int likes;
    private String categori;
    private int boothnum;
    private boolean Likable;
}
