package LlikelionKNU.KNUfest.domain.comment.repository;

import LlikelionKNU.KNUfest.domain.comment.entity.CommentEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "select * from booth_comment where booth_id= :boothId order by id limit :lim offset :offset",
            nativeQuery = true)
    List<CommentEntity> findAllOrderById(@Param("boothId") int boothId, @Param("lim") int limit, @Param("offset") int offset);
    @Query(value = "select * from booth_comment where booth_id= :boothId order by id desc limit :lim offset :off",
            nativeQuery = true)
    List<CommentEntity> findAllOrderByIdDESC(@Param("boothId") int boothId, @Param("lim") int limit, @Param("off") int offset);
}
