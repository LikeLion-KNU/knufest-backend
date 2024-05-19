package LlikelionKNU.KNUfest.domain.user.controller;

import LlikelionKNU.KNUfest.domain.user.dto.UserCount;
import LlikelionKNU.KNUfest.domain.user.service.UserCountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserCountService userCountService;


    @GetMapping()
    @Operation(summary = "유저 수 조회", description = "UserHash 수만큼의 값을 불러온다.")
    public ResponseEntity<UserCount> getUserCount(
    ){
        UserCount userCount = UserCount.builder()
                .count(userCountService.getUserCount()).build();

        return ResponseEntity.ok().body(userCount);
    }
}
