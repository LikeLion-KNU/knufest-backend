package LlikelionKNU.KNUfest.domain.comment.entity;


import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentDto;
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

    @Column(name = "password", length = 10)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booth_id")
    private BoothEntity booth;

    public CommentDto toDto(){
        return CommentDto.builder()
                .id(this.getId().intValue())
                .name(this.name)
                .password(this.password)
                .comment(this.comment)
                .created(this.getCreatedAt())
                .build();
    }
}
