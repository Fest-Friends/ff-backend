package gdgoc.team2.festfriends.concert.repository;

import gdgoc.team2.festfriends.concert.entity.Concert;
import gdgoc.team2.festfriends.concert.entity.FriendSearch;
import gdgoc.team2.festfriends.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendSearchRepository extends JpaRepository<FriendSearch, Long> {
    boolean existsByConcertAndUser(Concert concert, User user);
    FriendSearch findByConcertAndUser(Concert concert, User user);
    List<FriendSearch> findByConcert(Concert concert);

    @Query("SELECT fs FROM FriendSearch fs WHERE fs.concert = :concert " +
            "AND (:gender IS NULL OR fs.user.gender = :gender) " +
            "AND (:appreciationType IS NULL OR fs.user.listeningType = :listeningType)")
    List<FriendSearch> findByFilters(
            @Param("concert") Concert concert,
            @Param("gender") String gender,
            @Param("appreciationType") String listeningType,
            Pageable pageable
    );
}
