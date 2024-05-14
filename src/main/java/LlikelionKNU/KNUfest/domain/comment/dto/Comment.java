package LlikelionKNU.KNUfest.domain.comment.dto;

import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.comment.entity.CommentEntity;
import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
public class Comment {
    private Long id;
    private String name;
    private String comment;
    private LocalDateTime created;
    private Boolean deleteable;

    public static CommentEntity toEntity(CommentRequest commentRequest, BoothEntity booth, UserEntity user){
        return CommentEntity.builder()
                .name(commentRequest.getName())
                .comment(commentRequest.getComment())
                .booth(booth)
                .user(user)
                .build();
    }
}
