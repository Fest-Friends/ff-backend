package gdgoc.team2.festfriends.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String username; // 로그인 아이디

    private String password; // 로그인 비밀번호

    private String nickname; // 서비스 내의 이름

    private String mbti;

    private String musicPreference; // 음악취향

    private String listeningType; // 감상타입

    private String introduction; // 한줄소개

    private String volume; // 활동점수
}
