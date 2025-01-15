package gdgoc.team2.festfriends.user.controller;

import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.dto.UserIdResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/session")
public class UserSessionController {
    @GetMapping("/get-user-id")
    public ApiResponse<UserIdResponse> getUserIdFromSession(HttpServletRequest request) {

        Long userId = (Long) request.getSession().getAttribute("userId");
        UserIdResponse userIdResponse = new UserIdResponse(userId);

        if (userId == null) {
            return ApiResponse.<UserIdResponse>builder()
                    .success(false)
                    .message("세션이 존재하지 않습니다.")
                    .data(null)
                    .build();
        }

        // userId를 클라이언트에 전송
        return ApiResponse.<UserIdResponse>builder()
                .success(true)
                .message(null)
                .data(userIdResponse)
                .build();
    }
}
