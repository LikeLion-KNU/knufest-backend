package LlikelionKNU.KNUfest.domain.booth.controller;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBoothDto;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetailDto;
import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDto;
import LlikelionKNU.KNUfest.domain.booth.service.BoothService;
import LlikelionKNU.KNUfest.global.error.ErrorMessage;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/booth")
@RequiredArgsConstructor
public class BoothController {
    private final BoothService service;

    @GetMapping()
    @Operation(summary = "모든 부스정보 조회", description = "모든 부스의 id와 좋아요를 보내준다. 그 부스 목록에 뜨는 걸로 쓰면 됨")
    public ResponseEntity<AllBoothDto> getAllbooth(){
        AllBoothDto booths = service.getAllbooth();
        return ResponseEntity.ok().body(booths);
    }

    @GetMapping("{boothId}")
    @Operation(summary = "특정 부스정보 조회", description = "특정 부스의 id, 좋아요, 오래된 순 댓글 5개를 보내준다. 부스 별 페이지에 갖다 쓰면 됨" )
    public ResponseEntity<BoothDetailDto> getBooth(
            @PathVariable("boothId") int boothId
    ){
        BoothDetailDto boothDto = service.getBooth(boothId);
        return ResponseEntity.ok().body(boothDto);
    }

    @PatchMapping("{boothId}")
    @Operation(summary = "특정 부스 좋아요 업데이트", description = "특정 부스의 좋아요를 +1 한다.")
    public ResponseEntity<BasicResponse> updateLikes(
            @PathVariable("boothId") int boothId
    ){
        service.updateLikes(boothId);
        BasicResponse response = BasicResponse.builder()
                .message("좋아요를 성공적으로 업데이트(+1) 하였습니다.")
                .status(200)
                .build();
        return ResponseEntity.ok().body(response);
    }

}
