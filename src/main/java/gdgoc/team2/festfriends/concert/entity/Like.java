package gdgoc.team2.festfriends.concert.entity;

import gdgoc.team2.festfriends.user.entity.User;
import jakarta.persistence.*;

@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Concert concert;

    @ManyToOne
    private User user;
}
