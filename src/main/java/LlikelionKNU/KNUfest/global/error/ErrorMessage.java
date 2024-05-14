package LlikelionKNU.KNUfest.global.error;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ErrorMessage {
    private int status;
    private String message;
    private LocalDateTime timeStamp;
}
