package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;

public interface BoothService {
    AllBooth getAllbooth();
    BoothDetail getBooth(int id);
    void updateLikes(int id);

}
