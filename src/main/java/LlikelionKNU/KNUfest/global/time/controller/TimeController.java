package LlikelionKNU.KNUfest.global.time.controller;

import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.Basic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

@RestController("api/v1")
public class TimeController {
    @GetMapping("time")
    @Operation(summary = "서버 시간 조회", description = "서버 현재 시간을 보내준다.")
    public ResponseEntity<LocalDateTime> getServerTime(){
        return ResponseEntity.ok().body(LocalDateTime.now());
    }
}
