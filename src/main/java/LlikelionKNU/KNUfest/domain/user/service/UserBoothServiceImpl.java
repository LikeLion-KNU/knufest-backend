package LlikelionKNU.KNUfest.domain.user.service;

import LlikelionKNU.KNUfest.domain.booth.dto.Booth;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;
import LlikelionKNU.KNUfest.domain.user.repository.UserBoothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBoothServiceImpl implements UserBoothService{

    private final UserBoothRepository userBoothRepository;

    @Override
    public List<UserBoothEntity> getAllUserBooth(Long userId) {
        return userBoothRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<UserBoothEntity> getUserBooth(Long userId, Long boothId) {
        return userBoothRepository.findByUserIdAndBoothId(userId, boothId);
    }


}
