package gdgoc.team2.festfriends.user.dto;

import gdgoc.team2.festfriends.user.entity.User;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo {

    private String gender;

    private String nickname; // 서비스 내의 이름

    private String mbti;

    private String musicPreference; // 음악취향

    private String listeningType; // 감상타입

    private String introduction; // 한줄소개

    private int volume; // 활동점수

    public UserInfo(User user) {
        this.gender = user.getGender();
        this.nickname = user.getNickname();
        this.mbti = user.getMbti();
        this.musicPreference = user.getMusicPreference();
        this.listeningType = user.getListeningType();
        this.introduction = user.getIntroduction();
        this.volume = user.getVolume();
    }

}
