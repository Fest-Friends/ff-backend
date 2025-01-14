package gdgoc.team2.festfriends.user.service;

import gdgoc.team2.festfriends.concert.entity.Concert;
import gdgoc.team2.festfriends.concert.entity.FriendSearch;
import gdgoc.team2.festfriends.concert.repository.ConcertRepository;
import gdgoc.team2.festfriends.concert.repository.FriendSearchRepository;
import gdgoc.team2.festfriends.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FriendSearchRepository friendSearchRepository;
    private final ConcertRepository concertRepository;

    public void startFindFriends(Long concertId, User user) {

        Concert concert = concertRepository.findById(concertId).orElse(null);

        boolean alreadyExists = friendSearchRepository.existsByConcertAndUser(concert, user);

        if (alreadyExists) {
            throw new IllegalStateException("이미 해당 공연에 대한 친구 찾기 정보가 등록되어 있습니다.");
        }

        FriendSearch friendSearch = FriendSearch.builder()
                .concert(concert)
                .user(user)
                .build();

        friendSearchRepository.save(friendSearch);
    }

    public void cancelFindFriends(Long concertId, User user) {

        Concert concert = concertRepository.findById(concertId).orElse(null);

        FriendSearch friendSearch = friendSearchRepository.findByConcertAndUser(concert, user);

        if (friendSearch == null) {
            throw new IllegalStateException("해당 공연에 대한 친구 찾기 정보가 등록되지 않았습니다.");
        }

        friendSearchRepository.delete(friendSearch);
    }
}
