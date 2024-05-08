package LlikelionKNU.KNUfest.domain.booth.controller;

import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetailDto;
import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDto;
import LlikelionKNU.KNUfest.domain.booth.service.BoothService;
import LlikelionKNU.KNUfest.global.error.ErrorMessage;
import LlikelionKNU.KNUfest.global.error.NoExistException;
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
    public ResponseEntity<List<BoothDto>> getAllbooth(){
        List<BoothDto> booths = service.getAllbooth();
        return ResponseEntity.ok().body(booths);
    }

    @GetMapping("{boothId}")
    public ResponseEntity<BoothDetailDto> getBooth(
            @PathVariable("boothId") int boothId
    ){
        BoothDetailDto boothDto = service.getBooth(boothId);
        return ResponseEntity.ok().body(boothDto);
    }

    @PatchMapping("{boothId}")
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
