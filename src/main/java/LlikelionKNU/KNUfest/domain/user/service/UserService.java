package LlikelionKNU.KNUfest.domain.user.service;

import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import org.springframework.stereotype.Service;


public interface UserService {
    UserEntity getUserByHash(String userHash);
}
