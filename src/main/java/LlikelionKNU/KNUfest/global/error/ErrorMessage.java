package LlikelionKNU.KNUfest.global.error;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class ErrorMessage {
    private int status;
    private String message;
    private Date timeStamp;
}
