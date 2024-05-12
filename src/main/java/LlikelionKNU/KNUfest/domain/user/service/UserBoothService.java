package LlikelionKNU.KNUfest.domain.user.service;


import LlikelionKNU.KNUfest.domain.booth.dto.Booth;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;

import java.util.List;

public interface UserBoothService {
    List<UserBoothEntity> getAllUserBooth(Long userId);
    UserBoothEntity getUserBooth(Long userId, Long boothId);
}
