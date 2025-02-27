package gdgoc.team2.festfriends.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentUserResponse {
    Long userId;
    String nickname;
}
