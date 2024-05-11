package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBoothDto;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetailDto;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDto;

import java.util.List;

public interface BoothService {
    AllBoothDto getAllbooth();
    BoothDetailDto getBooth(int id);
    void updateLikes(int id);

}
