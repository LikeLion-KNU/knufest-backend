package LlikelionKNU.KNUfest.domain.booth.entity;

import LlikelionKNU.KNUfest.domain.comment.entity.CommentEntity;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;
import LlikelionKNU.KNUfest.global.basic.BasicEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "BOOTH")
public class BoothEntity extends BasicEntity {

    @Column(name="booth_name")
    private String boothName;

    @Column(name="likes")
    @ColumnDefault("0")
    private int likes;


    @OneToMany(mappedBy = "booth", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    @OneToMany(mappedBy = "boothEntity", fetch = FetchType.LAZY)
    private UserBoothEntity userBoothEntity;

    @ElementCollection
    @CollectionTable(
            name = "URL_LIST",
            joinColumns = @JoinColumn(name="booth_id", referencedColumnName = "id")
    )
    private List<String> urls;
}
