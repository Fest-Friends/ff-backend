package gdgoc.team2.festfriends.user.controller;


import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.dto.LoginRequest;
import gdgoc.team2.festfriends.user.dto.SignupRequest;
import gdgoc.team2.festfriends.user.service.UserAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @Autowired
    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/signup")
    public ApiResponse<Void> signup(@RequestBody SignupRequest signupRequest) {
        try {
            userAuthService.signup(signupRequest);
            return ApiResponse.<Void>builder()
                    .success(true)
                    .message(null)
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Void>builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/login")
    public ApiResponse<Void> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
        try {
            userAuthService.login(loginRequest, httpServletRequest);
            return ApiResponse.<Void>builder()
                    .success(true)
                    .message(null)
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Void>builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
