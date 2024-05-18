package LlikelionKNU.KNUfest.domain.comment.controller;

import LlikelionKNU.KNUfest.domain.comment.dto.Comment;
import LlikelionKNU.KNUfest.domain.comment.dto.CommentRequest;
import LlikelionKNU.KNUfest.domain.comment.service.CommentService;
import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @GetMapping("booth/{categori}/{boothNum}/comment")
    @Operation(summary = "추가 댓글 조회", description = "해당 부스의 추가 댓글내용을 제공한다. boothNum(부스 번호), categori(pub : 주점, comp : 복합, other: 기타)를 넘겨줘야 함. perpage = 요청당 댓글 개수 한 페이지 당 통일되게 보내야 됨, page = 요청 댓글 set 순번. order = 댓글 정렬순서 최신순은 'desc' 오래된 순은 'default' 이다. deletable은 유저기반 삭제 가능 여부.")
    public ResponseEntity<List<Comment>> getExtraCommentPage(
            @PathVariable("boothNum") int boothNum,
            @PathVariable("categori") String categori,
            @RequestParam("perpage") int perpage,
            @RequestParam("page") int page,
            @RequestParam("order") String order,
            @RequestParam("userHash") String userHash
    ){
        List<Comment> result = service.getCommentPage(boothNum, categori, perpage, page, order, userHash);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("booth/{categori}/{boothNum}/comment")
    @Operation(summary = "특정부스 댓글 생성", description = "특정 부스에 댓글을 생성한다. boothNum(부스 번호), categori(pub : 주점, comp : 복합, other: 기타)를 넘겨줘야 함.")
    public ResponseEntity<BasicResponse> postComment(
            @PathVariable("boothNum") int boothNum,
            @PathVariable("categori") String categori,
            @RequestParam("userHash") String userHash,
            @RequestBody CommentRequest commentRequest
    ){

        Long id = service.postComment(boothNum, categori, commentRequest, userHash);

        BasicResponse response = BasicResponse.builder()
                .message("부스에 댓글을 생성하였습니다.")
                .status(201)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.created(URI.create("comment"+id)).body(response);
    }

    @DeleteMapping("comment/{commentId}")
    @Operation(summary = "특정 댓글 삭제", description = "특정 댓글을 userHash 값을 통해 비교하여 삭제한다. 해당 댓글의 id 값을 보내야함")
    public ResponseEntity<BasicResponse> deleteComment(
            @PathVariable("commentId") Long commentId,
            @RequestParam("userHash") String userHash
    ){
        service.deleteComment(commentId, userHash);

        BasicResponse basicResponse = BasicResponse.builder()
                .message("성공적으로 삭제되었습니다.")
                .status(200)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(basicResponse);
    }
}
