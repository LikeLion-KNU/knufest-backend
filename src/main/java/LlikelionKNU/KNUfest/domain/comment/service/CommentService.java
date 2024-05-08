package LlikelionKNU.KNUfest.domain.comment.service;

import LlikelionKNU.KNUfest.domain.comment.dto.CommentDto;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentPage(int boothId, int perpage, int page, String order);
    int postComment(int id, CommentRequest comment);
    void deleteComment(int commentId, String password);

}
