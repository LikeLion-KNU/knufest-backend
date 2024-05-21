package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothLike;
import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;

public interface BoothService {
    AllBooth getAllbooth(String userHash);
    BoothDetail getBooth(int boothNum, String categori, String userHash);
    BoothLike updateLikes(int boothNum, String categori, String userHash);
    BoothEntity findByBoothnumAndCategori(int boothnum, String categori);

    BoothEntity findById(Long id);

}
