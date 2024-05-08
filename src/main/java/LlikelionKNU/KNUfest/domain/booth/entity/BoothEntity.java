package LlikelionKNU.KNUfest.domain.booth.entity;

import LlikelionKNU.KNUfest.domain.comment.entity.CommentEntity;
import LlikelionKNU.KNUfest.global.basic.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOTH")
public class BoothEntity extends BasicEntity {

    @Column(name="likes")
    @ColumnDefault("0")
    private int likes;

    @OneToMany(mappedBy = "booth", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    @ElementCollection
    @CollectionTable(
            name = "URL_LIST",
            joinColumns = @JoinColumn(name="booth_id", referencedColumnName = "id")
    )
    private List<String> urls;
}
