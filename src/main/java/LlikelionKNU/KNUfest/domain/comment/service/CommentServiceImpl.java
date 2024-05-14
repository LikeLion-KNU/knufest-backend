package LlikelionKNU.KNUfest.domain.comment.service;

import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.booth.repository.BoothRepository;
import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;
import LlikelionKNU.KNUfest.domain.comment.entity.CommentEntity;
import LlikelionKNU.KNUfest.domain.comment.repository.CommentRepository;
import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import LlikelionKNU.KNUfest.domain.user.service.UserService;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import LlikelionKNU.KNUfest.global.error.UserHashWrongException;
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
    private final UserService userService;


    @Override
    public List<Comment> getCommentPage(Long boothId, int perpage, int page, String order, String userHash) {

        Optional<BoothEntity> boothOp =  boothRepository.findById(boothId);
        BoothEntity booth;
        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
        }else{
            booth = boothOp.get();
        }

        List<CommentEntity> comments;
        List<Comment> result = new ArrayList<>();

        int offset = (page-1) * perpage;

        if(order.equals("desc")){
            comments = commentRepository.findAllOrderByIdDESC(booth.getId(), perpage, offset);
        }else{
            comments = commentRepository.findAllOrderById(booth.getId(), perpage, offset);
        }

        if(!comments.isEmpty()) {
            for (CommentEntity c : comments) {
                Comment comment = c.toDto();
                if (c.getUser().getUserHash().equals(userHash)) {
                    comment.setDeleteable(true);
                } else {
                    comment.setDeleteable(false);
                }
                result.add(comment);
            }
        }
        return result;

    }

    @Override
    public Long postComment(Long boothId, CommentRequest commentRequest, String userHash) {


        UserEntity user = userService.getUserByHash(userHash);
        Optional<BoothEntity> boothOp =  boothRepository.findById(boothId);
        BoothEntity booth;
        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
        }else{
            booth = boothOp.get();
        }

        CommentEntity newComment = Comment.toEntity(commentRequest, booth, user);
        commentRepository.save(newComment);
        return newComment.getId();
    }

    @Override
    public void deleteComment(Long commentId, String userHash) {
        Optional<CommentEntity> commentOp = commentRepository.findById(commentId);

        if(commentOp.isEmpty()) {
            throw new NoExistException("해당 댓글은 없습니다. (id 확인 요망)");
        }else{
            if(commentOp.get().getUser().getUserHash().equals(userHash)){
                commentRepository.delete(commentOp.get());
            }else{
                throw new UserHashWrongException("userHash 값이 일치하지 않습니다.");
            }
        }
    }
}
