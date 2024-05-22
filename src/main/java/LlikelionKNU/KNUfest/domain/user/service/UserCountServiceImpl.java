package LlikelionKNU.KNUfest.domain.user.service;

import LlikelionKNU.KNUfest.domain.user.entity.UserCountEntity;
import LlikelionKNU.KNUfest.domain.user.repository.UserCountRepository;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCountServiceImpl implements UserCountService {
    private final UserCountRepository userCountRepository;

    @Override
    public Long getUserCount() {

        Optional<UserCountEntity> userCountEntity = userCountRepository.findById(1L);

        if(userCountEntity.isEmpty()){
            throw new NoExistException("유저 수가 없습니다.");
        }else{
            return userCountEntity.get().getCount();
        }

    }
}
