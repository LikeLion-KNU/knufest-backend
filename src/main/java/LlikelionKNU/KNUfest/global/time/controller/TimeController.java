package LlikelionKNU.KNUfest.global.time.controller;

import LlikelionKNU.KNUfest.global.basic.BasicResponse;
import jakarta.persistence.Basic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController("api/v1")
public class TimeController {
    @GetMapping("time")
    public ResponseEntity<Instant> getServerTime(){
        return ResponseEntity.ok().body(Instant.now());
    }
}
