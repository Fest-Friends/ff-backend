package gdgoc.team2.festfriends.user.controller;

import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.entity.User;
import gdgoc.team2.festfriends.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/concert/{concertId}/find-friends")
    public ApiResponse<List<User>> getFindFriends(
            @PathVariable Long concertId,
            @RequestParam(required = false, defaultValue = "무관") String gender,
            @RequestParam(required = false, defaultValue = "무관") String listeningType,
            @RequestParam(required = false, defaultValue = "무관") String volumeSort) {

        try {
            List<User> userList = userService.getFindFriendList(concertId, gender, listeningType, volumeSort);
            return ApiResponse.<List<User>>builder()
                    .success(true)
                    .message(null)
                    .data(userList)
                    .build();
        }catch(Exception e){
            return ApiResponse.<List<User>>builder()
                    .success(false)
                    .message(null)
                    .data(Collections.emptyList())
                    .build();
        }
    }


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

    @DeleteMapping("/concert/{concertId}/find-friends/cancel")
    public ApiResponse<Void> cancelFindFriends(@PathVariable Long concertId, @SessionAttribute("currentUser") User user) {
        try{
            userService.cancelFindFriends(concertId, user);
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
