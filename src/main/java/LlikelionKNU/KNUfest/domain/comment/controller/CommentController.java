package LlikelionKNU.KNUfest.domain.comment.controller;

import LlikelionKNU.KNUfest.domain.comment.dto.CommentDto;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;
import LlikelionKNU.KNUfest.domain.comment.service.CommentService;
import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "추가 댓글 조회", description = "해당 부스의 추가 댓글내용을 제공한다. perpage = 요청당 댓글 개수 한 페이지 당 통일되게 보내야 됨, page = 요청 댓글 set 순번. order = 댓글 정렬순서 최신순은 'desc' 오래된 순은 'default' 이다. ")
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
    @Operation(summary = "특정부스 댓글 생성", description = "특정 부스에 댓글을 생성한다.")
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
    @Operation(summary = "특정 댓글 삭제", description = "특정 댓글을 password 가 일치할 경우 삭제한다.")
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
