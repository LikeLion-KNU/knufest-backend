package LlikelionKNU.KNUfest.domain.comment.controller;

import LlikelionKNU.KNUfest.domain.comment.dto.CommentDto;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;
import LlikelionKNU.KNUfest.domain.comment.service.CommentService;
import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @GetMapping("booth/{boothId}/comment")
    public ResponseEntity<List<CommentDto>> getExtraCommentPage(
            @PathVariable("boothId") int boothId,
            @RequestParam("perpage") int perpage,
            @RequestParam("page") int page,
            @RequestParam("order") String order
    ){
        List<CommentDto> result = service.getCommentPage(boothId, perpage, page, order);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("booth/{boothId}/comment")
    public ResponseEntity<BasicResponse> postComment(
            @PathVariable("boothId") int boothId,
            @RequestBody CommentRequest comment
    ){

        int id = service.postComment(boothId, comment);

        BasicResponse response = BasicResponse.builder()
                .message(boothId+"번 부스에 댓글을 생성하였습니다.")
                .status(201)
                .build();

        return ResponseEntity.created(URI.create("comment"+id)).body(response);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<BasicResponse> deleteComment(
            @PathVariable("commentId") int commentId,
            @RequestParam("password") String password
    ){

        service.deleteComment(commentId, password);

        BasicResponse basicResponse = BasicResponse.builder()
                .message("성공적으로 삭제되었습니다.")
                .status(200)
                .build();

        return ResponseEntity.ok().body(basicResponse);
    }
}
