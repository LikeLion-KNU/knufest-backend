package LlikelionKNU.KNUfest.domain.user.entity;


import LlikelionKNU.KNUfest.global.basic.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity extends BasicEntity {

    @Column(name = "userHash")
    private String userHash;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserBoothEntity userBooth;
}
