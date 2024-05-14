package LlikelionKNU.KNUfest.domain.comment.entity;


import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import LlikelionKNU.KNUfest.global.basic.BasicEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "BOOTH_COMMENT")
public class CommentEntity extends BasicEntity {

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boothId")
    private BoothEntity booth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserEntity user;

    public Comment toDto(){
        return Comment.builder()
                .id(this.getId())
                .name(this.name)
                .comment(this.comment)
                .created(this.getCreatedAt())
                .build();
    }
}
