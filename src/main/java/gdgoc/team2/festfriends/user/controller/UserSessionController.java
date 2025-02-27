package gdgoc.team2.festfriends.user.controller;

import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.dto.CurrentUserResponse;
import gdgoc.team2.festfriends.user.service.UserSessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/me")
@RequiredArgsConstructor
public class UserSessionController {

    private final UserSessionService userSessionService;

    @GetMapping
    public ResponseEntity<ApiResponse<CurrentUserResponse>> getCurrentUser(HttpServletRequest request) {
        CurrentUserResponse currentUserResponse = userSessionService.getCurrentUser(request);
        return ResponseEntity.ok(
                ApiResponse.<CurrentUserResponse>builder()
                        .success(true)
                        .message(null)
                        .data(currentUserResponse)
                        .build()
        );
    }
}
