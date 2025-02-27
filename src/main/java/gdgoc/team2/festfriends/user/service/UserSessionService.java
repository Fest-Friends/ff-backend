package gdgoc.team2.festfriends.user.service;

import gdgoc.team2.festfriends.global.dto.ApiResponse;
import gdgoc.team2.festfriends.user.dto.CurrentUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {

    public CurrentUserResponse getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new IllegalStateException("세션이 존재하지 않습니다.");
        }

        Long userId = (Long) session.getAttribute("userId");
        String nickname = (String) session.getAttribute("nickname");

        return new CurrentUserResponse(userId, nickname);
    }
}
