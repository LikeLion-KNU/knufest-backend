package LlikelionKNU.KNUfest.domain.comment.service;

import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentPage(Long boothId, int perpage, int page, String order, String userHash);
    Long postComment(Long id, CommentRequest comment, String userHash);
    void deleteComment(Long commentId, String password);

}
