package LlikelionKNU.KNUfest.domain.comment.service;

import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.booth.repository.BoothRepository;
import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;
import LlikelionKNU.KNUfest.domain.comment.entity.CommentEntity;
import LlikelionKNU.KNUfest.domain.comment.repository.CommentRepository;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import LlikelionKNU.KNUfest.global.error.PasswordWrongException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final BoothRepository boothRepository;
    @Override
    public List<Comment> getCommentPage(int boothId, int perpage, int page, String order) {

        Optional<BoothEntity> boothOp =  boothRepository.findById(Long.valueOf(boothId));
        List<CommentEntity> comments;
        List<Comment> result;

        if(boothOp.isEmpty()){
            throw new NoExistException("댓글을 불러올 부스 정보가 없습니다. (id 확인 요망)");
        }else{
            result = new ArrayList<>();

            int offset = (page-1) * perpage;

            if(order.equals("desc")){
                comments = commentRepository.findAllOrderByIdDESC(boothId, perpage, offset);
            }else{
                comments = commentRepository.findAllOrderById(boothId, perpage, offset);
            }
            for(CommentEntity c: comments){
                result.add(c.toDto());
            }
            return result;
        }
    }

    @Override
    public int postComment(int boothId, CommentRequest commentRequest) {

        Optional<BoothEntity> boothOp =  boothRepository.findById(Long.valueOf(boothId));

        Comment comment = Comment.builder()
                .name(commentRequest.getName())
                .comment(commentRequest.getComment())
                .password(commentRequest.getPassword())
                .build();

        if(boothOp.isEmpty()){
            throw new NoExistException("댓글을 추가할 부스 정보가 없습니다. (id 확인 요망)");
        }else{
            CommentEntity newComment = comment.toEntity(boothOp.get());
            commentRepository.save(newComment);
            return newComment.getId().intValue();
        }
    }

    @Override
    public void deleteComment(int commentId, String password) {
        Optional<CommentEntity> commentOp = commentRepository.findById(Long.valueOf(commentId));

        if(commentOp.isEmpty()) {
            throw new NoExistException("해당 댓글은 없습니다. (id 확인 요망)");
        }else{
            if(commentOp.get().getPassword().equals(password)){
                commentRepository.delete(commentOp.get());
            }else{
                throw new PasswordWrongException("비밀번호가 틀렸습니다.");
            }
        }
    }
}
