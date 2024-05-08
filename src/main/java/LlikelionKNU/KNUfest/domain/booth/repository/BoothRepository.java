package LlikelionKNU.KNUfest.domain.booth.repository;

import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoothRepository extends JpaRepository<BoothEntity, Long> {

    Optional<BoothEntity> findById(Long id);
}
