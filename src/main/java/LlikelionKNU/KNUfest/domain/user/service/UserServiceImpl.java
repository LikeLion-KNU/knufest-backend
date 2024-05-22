package LlikelionKNU.KNUfest.domain.user.service;

import LlikelionKNU.KNUfest.domain.user.entity.UserCountEntity;
import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import LlikelionKNU.KNUfest.domain.user.repository.UserCountRepository;
import LlikelionKNU.KNUfest.domain.user.repository.UserRepository;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserCountRepository userCountRepository;

    @Override
    public UserEntity getUserByHash(String userHash) {

        Optional<UserEntity> userEntity = userRepository.findByUserHash(userHash);
        Optional<UserCountEntity> userCountEntity = userCountRepository.findById(1L);

        UserEntity newUser;
        UserCountEntity newCount;

        if(userEntity.isEmpty()){
            newUser = UserEntity.builder()
                    .userHash(userHash).build();
            userRepository.save(newUser);

            if(userCountEntity.isEmpty()){
                throw new NoExistException("유저수가 없습니다.");
            }else{
                newCount = userCountEntity.get();
                newCount.setCount(newCount.getCount()+1);

                userCountRepository.save(newCount);
            }

            return newUser;
        }
        else{
            return userEntity.get();
        }

    }
}
