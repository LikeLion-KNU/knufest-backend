package LlikelionKNU.KNUfest.domain.user.service;

import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import LlikelionKNU.KNUfest.domain.user.repository.UserRepository;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserEntity getUserByHash(String userHash) {

        Optional<UserEntity> userEntity = userRepository.findByUserHash(userHash);
        UserEntity newUser;
        if(userEntity.isEmpty()){
            newUser = UserEntity.builder()
                    .userHash(userHash).build();
            userRepository.save(newUser);

            return newUser;
        }
        else{
            return userEntity.get();
        }

    }
}
