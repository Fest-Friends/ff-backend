package gdgoc.team2.festfriends.user.controller;

import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.entity.User;
import gdgoc.team2.festfriends.user.service.UserAuthService;
import gdgoc.team2.festfriends.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/concert/{concertId}/find-friends/start")
    public ApiResponse<Void> startFindFriends(@PathVariable Long concertId, @SessionAttribute("currentUser") User user) {
        try{
            userService.startFindFriends(concertId, user);
            return ApiResponse.<Void>builder()
                    .success(true)
                    .message(null)
                    .data(null)
                    .build();
        }catch (Exception e){
            return ApiResponse.<Void>builder()
                    .success(false)
                    .message(null)
                    .data(null)
                    .build();
        }
    }
}
