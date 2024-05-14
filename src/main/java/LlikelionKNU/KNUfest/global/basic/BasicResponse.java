package LlikelionKNU.KNUfest.global.basic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BasicResponse {
    private int status;
    private String message;
    private LocalDateTime timeStamp;
}
