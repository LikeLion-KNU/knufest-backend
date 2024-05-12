package LlikelionKNU.KNUfest.domain.user.repository;

import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserHash(String userHash);
}
