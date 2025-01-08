package gdgoc.team2.festfriends.concert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // 공연 이름

    private String location; // 공연 장소

    private String period; // 공연 기간

    private String imageUrl; // 공연 이미지

    private Long likes;

    private Long searchFriends;
}
