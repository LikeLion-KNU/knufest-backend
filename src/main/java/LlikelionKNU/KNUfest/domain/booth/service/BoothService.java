package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;

public interface BoothService {
    AllBooth getAllbooth(String userHash);
    BoothDetail getBooth(Long id, String userHash);
    void updateLikes(Long id);

}
