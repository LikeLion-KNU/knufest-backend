package LlikelionKNU.KNUfest.domain.user.service;


import LlikelionKNU.KNUfest.domain.booth.dto.Booth;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;

import java.util.List;
import java.util.Optional;

public interface UserBoothService {
    List<UserBoothEntity> getAllUserBooth(Long userId);
    Optional<UserBoothEntity> getUserBooth(Long userId, Long boothId);
}
