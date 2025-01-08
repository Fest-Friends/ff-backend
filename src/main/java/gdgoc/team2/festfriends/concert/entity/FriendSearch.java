package gdgoc.team2.festfriends.concert.entity;

import gdgoc.team2.festfriends.user.entity.User;
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
public class FriendSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Concert concert;

    @ManyToOne
    private User user;
}
