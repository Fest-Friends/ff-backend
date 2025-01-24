package gdgoc.team2.festfriends.concert.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertResponse {
    private Long id;

    private String name; // 공연 이름

    private String location; // 공연 장소

    private String period; // 공연 기간

    private String imageUrl; // 공연 이미지

    private Long likes;

    private Long searchFriends;

    private Boolean isLike;
}
