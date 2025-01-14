package gdgoc.team2.festfriends.concert.repository;

import gdgoc.team2.festfriends.concert.entity.Concert;
import gdgoc.team2.festfriends.concert.entity.FriendSearch;
import gdgoc.team2.festfriends.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendSearchRepository extends JpaRepository<FriendSearch, Long> {
    boolean existsByConcertAndUser(Concert concert, User user);
    FriendSearch findByConcertAndUser(Concert concert, User user);
}
