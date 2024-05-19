package LlikelionKNU.KNUfest.domain.comment.service;

import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentPage(int boothNum, String catigori,int page, String userHash);
    Long postComment(int boothNum, String catigori, CommentRequest comment, String userHash);
    void deleteComment(Long commentId, String password);

}
