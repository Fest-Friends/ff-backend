package gdgoc.team2.festfriends.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserIdResponse {
    Long userId;

    public UserIdResponse(Long userId) {
        this.userId = userId;
    }
}
