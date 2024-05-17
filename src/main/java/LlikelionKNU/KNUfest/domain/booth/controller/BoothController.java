package LlikelionKNU.KNUfest.domain.booth.controller;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;
import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import LlikelionKNU.KNUfest.domain.booth.service.BoothService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/booth")
@RequiredArgsConstructor
public class BoothController {
    private final BoothService service;

    @GetMapping()
    @Operation(summary = "모든 부스정보 조회", description = "모든 부스의 id와 좋아요를 보내준다. 그 부스 목록에 뜨는 걸로 쓰면 됨.  likable은 유저별 좋아요 누르기 가능여부")
    public ResponseEntity<AllBooth> getAllbooth(
            @RequestParam("userHash") String userHash
    ){
        AllBooth booths = service.getAllbooth(userHash);
        return ResponseEntity.ok().body(booths);
    }

    @GetMapping("/{categori}/{boothNum}")
    @Operation(summary = "특정 부스정보 조회", description = "특정 부스의 id, 좋아요, 오래된 순 댓글 5개를 보내준다. 부스 별 페이지에 갖다 쓰면 됨. categori: 부스 종류(주점 : pub, 복합 : comp, 기타 : other), boothnum: 부스번호임. likable은 유저별 좋아요 누르기 가능여부" )
    public ResponseEntity<BoothDetail> getBooth(
            @PathVariable("boothNum") int boothNum,
            @PathVariable("categori") String categori,
            @RequestParam("userHash") String userHash
    ){
        BoothDetail boothDto = service.getBooth(boothNum, categori, userHash);
        return ResponseEntity.ok().body(boothDto);
    }

    @PatchMapping("{categori}/{boothNum}")
    @Operation(summary = "특정 부스 좋아요 업데이트", description = "특정 부스의 좋아요를 변경한다.")
    public ResponseEntity<BasicResponse> updateLikes(
            @PathVariable("boothNum") int boothNum,
            @PathVariable("categori") String categori,
            @RequestParam("userHash") String userHash
    ){
        String message = service.updateLikes(boothNum, categori, userHash);
        BasicResponse response = BasicResponse.builder()
                .message(message)
                .status(200)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

}
