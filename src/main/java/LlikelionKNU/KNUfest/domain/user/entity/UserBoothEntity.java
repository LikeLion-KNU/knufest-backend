package LlikelionKNU.KNUfest.domain.user.entity;

import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserBoothEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boothId")
    private BoothEntity boothEntity;

}
