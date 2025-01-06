package gdgoc.team2.festfriends.concert.repository;

import gdgoc.team2.festfriends.concert.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
