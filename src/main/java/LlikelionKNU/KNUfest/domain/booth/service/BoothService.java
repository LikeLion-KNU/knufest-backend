package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetailDto;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDto;

import java.util.List;

public interface BoothService {
    List<BoothDto> getAllbooth();
    BoothDetailDto getBooth(int id);
    void updateLikes(int id);

}
