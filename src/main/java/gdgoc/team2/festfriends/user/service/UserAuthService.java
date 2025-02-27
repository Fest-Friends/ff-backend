package gdgoc.team2.festfriends.user.service;

import gdgoc.team2.festfriends.user.dto.LoginRequest;
import gdgoc.team2.festfriends.user.dto.SignupRequest;
import gdgoc.team2.festfriends.user.entity.User;
import gdgoc.team2.festfriends.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (!signupRequest.getPassword().equals(signupRequest.getRetypePassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        User user = User.builder()
                .username(signupRequest.getUsername())
                .password(signupRequest.getPassword())
                .build();

        userRepository.save(user);
    }

    public void login(LoginRequest loginRequest, HttpServletRequest httpServletRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호를 다시 확인해주세요.");
        }

        System.out.println("세션이 생성되었습니다.");
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("userId", user.getId());
        httpSession.setAttribute("nickname", user.getNickname());
    }
}
