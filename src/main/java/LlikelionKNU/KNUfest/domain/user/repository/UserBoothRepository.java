package LlikelionKNU.KNUfest.domain.user.repository;

import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBoothRepository extends JpaRepository<UserBoothEntity, Long> {

    List<UserBoothEntity> findAllByUserId(Long userId);

    UserBoothEntity findByUserIdAndBoothId(Long userId, Long BoothId);
}
