package LlikelionKNU.KNUfest.domain.user.repository;

import LlikelionKNU.KNUfest.domain.user.entity.UserCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCountRepository extends JpaRepository<UserCountEntity, Long> {

    Optional<UserCountEntity> findById(Long id);
}
