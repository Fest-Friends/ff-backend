package gdgoc.team2.festfriends.user.controller;


import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.dto.LoginRequest;
import gdgoc.team2.festfriends.user.dto.SignupRequest;
import gdgoc.team2.festfriends.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {

    private final UserService userService;

    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApiResponse<Void> signup(SignupRequest signupRequest) {
        try{
            userService.signup(signupRequest);
            return ApiResponse.<Void>builder()
                    .success(true)
                    .message(null)
                    .data(null)
                    .build();
        }catch(Exception e){
            return ApiResponse.<Void>builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/login")
    public ApiResponse<Void> login(LoginRequest loginRequest, HttpServletRequest httpServletRequest){
        try{
            userService.login(loginRequest, httpServletRequest);
            return ApiResponse.<Void>builder()
                    .success(true)
                    .message(null)
                    .data(null)
                    .build();
        }catch(Exception e){
            return ApiResponse.<Void>builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
