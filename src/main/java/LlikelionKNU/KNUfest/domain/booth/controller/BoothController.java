package LlikelionKNU.KNUfest.domain.booth.controller;

import LlikelionKNU.KNUfest.domain.booth.dto.BoothDto;
import LlikelionKNU.KNUfest.domain.booth.service.BoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BoothController {
    private final BoothService service;

    @GetMapping("booth")
    public ResponseEntity<List<BoothDto>> getAllbooth(){
        List<BoothDto> booths = service.getAllbooth();
        return ResponseEntity.ok().body(booths);
    }

}
