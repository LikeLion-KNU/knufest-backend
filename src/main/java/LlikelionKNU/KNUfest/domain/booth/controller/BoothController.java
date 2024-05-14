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
    @Operation(summary = "모든 부스정보 조회", description = "모든 부스의 id와 좋아요를 보내준다. 그 부스 목록에 뜨는 걸로 쓰면 됨")
    public ResponseEntity<AllBooth> getAllbooth(
            @RequestParam("userHash") String userHash
    ){
        AllBooth booths = service.getAllbooth(userHash);
        return ResponseEntity.ok().body(booths);
    }

    @GetMapping("{boothId}")
    @Operation(summary = "특정 부스정보 조회", description = "특정 부스의 id, 좋아요, 오래된 순 댓글 5개를 보내준다. 부스 별 페이지에 갖다 쓰면 됨" )
    public ResponseEntity<BoothDetail> getBooth(
            @PathVariable("boothId") Long boothId,
            @RequestParam("userHash") String userHash
    ){
        BoothDetail boothDto = service.getBooth(boothId, userHash);
        return ResponseEntity.ok().body(boothDto);
    }

    @PatchMapping("{boothId}")
    @Operation(summary = "특정 부스 좋아요 업데이트", description = "특정 부스의 좋아요를 변경한다.")
    public ResponseEntity<BasicResponse> updateLikes(
            @PathVariable("boothId") Long boothId,
            @RequestParam("userHash") String userHash
    ){
        String message = service.updateLikes(boothId, userHash);
        BasicResponse response = BasicResponse.builder()
                .message(message)
                .status(200)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

}
