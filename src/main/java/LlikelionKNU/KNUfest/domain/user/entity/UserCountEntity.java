package LlikelionKNU.KNUfest.domain.user.entity;


import LlikelionKNU.KNUfest.global.basic.BasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usercount")
public class UserCountEntity extends BasicEntity {

    @Column(name = "user_count")
    private Long count;

}
