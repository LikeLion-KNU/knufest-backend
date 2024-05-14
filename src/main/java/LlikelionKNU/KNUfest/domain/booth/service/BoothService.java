package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;
import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;

public interface BoothService {
    AllBooth getAllbooth(String userHash);
    BoothDetail getBooth(Long id, String userHash);
    String updateLikes(Long id, String userHash);

    BoothEntity findById(Long id);

}
