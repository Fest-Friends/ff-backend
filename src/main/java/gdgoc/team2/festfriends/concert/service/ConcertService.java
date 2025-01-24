package gdgoc.team2.festfriends.concert.service;

import gdgoc.team2.festfriends.concert.dto.ConcertResponse;
import gdgoc.team2.festfriends.concert.entity.Concert;
import gdgoc.team2.festfriends.concert.repository.ConcertRepository;
import gdgoc.team2.festfriends.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;

    public List<ConcertResponse> getConcerts() {
        List<Concert> concerts = concertRepository.findAll();

        List<ConcertResponse> concertResponses = new ArrayList<>();
        for (Concert concert : concerts) {
            concertResponses.add(
                    ConcertResponse.builder()
                            .id(concert.getId())
                            .name(concert.getName())
                            .location(concert.getLocation())
                            .period(concert.getPeriod())
                            .imageUrl(concert.getImageUrl())
                            .likes(concert.getLikes())
                            .searchFriends(concert.getSearchFriends())
                            .isLike(false) // 현재 사용자가 좋아요 눌러둔 공연진지 확인 후 설정. 향후 개발 필요
                            .build());
        }

        return concertResponses;
    }

    public List<ConcertResponse> getConcertsByCategory(String category) {
        List<Concert> concerts = concertRepository.findByCategory(category);

        List<ConcertResponse> concertResponses = new ArrayList<>();
        for (Concert concert : concerts) {
            concertResponses.add(
                    ConcertResponse.builder()
                            .id(concert.getId())
                            .name(concert.getName())
                            .location(concert.getLocation())
                            .period(concert.getPeriod())
                            .imageUrl(concert.getImageUrl())
                            .likes(concert.getLikes())
                            .searchFriends(concert.getSearchFriends())
                            .isLike(false) // 현재 사용자가 좋아요 눌러둔 공연진지 확인 후 설정. 향후 개발 필요
                            .build());
        }

        return concertResponses;
    }


    public void crawlingConcerts() {
        try {
            String url = "https://tickets.interpark.com/contents/genre/concert";
            Document doc = Jsoup.connect(url).get();

            // 카테고리 정보 추출
            Elements categoryElements = doc.select("button.genre-tab-item span");
            List<String> categories = categoryElements.stream()
                    .map(Element::text)
                    .collect(Collectors.toList());

            // 공연 항목들 추출
            Elements concertItems = doc.select("a.TicketItem_ticketItem__H51Vs");

            for (Element item : concertItems) {
                String name = item.select("li.TicketItem_goodsName__Ju76j").text();
                String location = item.select("li.TicketItem_placeName__ls_9C").text();
                String period = item.select("li.TicketItem_playDate__5ePr2").text();
                String imageUrl = "https://tickets.interpark.com" + item.select("img.TicketItem_image__U6xq6").attr("src");

                // 카테고리 매칭 로직 개선: 공연 항목과 관련된 카테고리를 추출
                String category = "기타"; // 기본값은 기타

                // 카테고리 정보 추출
                for (String cat : categories) {
                    if (name.contains(cat)) {
                        category = cat;
                        break;
                    }
                }

                // 공연 객체 생성 및 저장
                Concert concert = Concert.builder()
                        .imageUrl(imageUrl)
                        .name(name)
                        .location(location)
                        .period(period)
                        .likes(0L)
                        .searchFriends(0L)
                        .category(category)
                        .build();

                concertRepository.save(concert);
            }
        } catch (IOException e) {
            System.err.println("크롤링 중 오류 발생: " + e.getMessage());
        }
    }


//    public void crawlingConcerts() {
//
//        try {
//            String url = "https://tickets.interpark.com/contents/genre/concert";
//            Document doc = Jsoup.connect(url).get();
//
//            Elements concertItems = doc.select("a.TicketItem_ticketItem__H51Vs");
//
//            for (Element item : concertItems) {
//
//                String name = item.select("li.TicketItem_goodsName__Ju76j").text();
//                String location = item.select("li.TicketItem_placeName__ls_9C").text();
//                String period = item.select("li.TicketItem_playDate__5ePr2").text();
//                String imageUrl = "https://tickets.interpark.com" + item.select("img.TicketItem_image__U6xq6").attr("src");
//
//                Concert concert = Concert.builder()
//                        .imageUrl(imageUrl)
//                        .name(name)
//                        .location(location)
//                        .period(period)
//                        .likes(0L)
//                        .searchFriends(0L)
//                        .build();
//
//                concertRepository.save(concert);
//            }
//        } catch (IOException e) {
//            System.err.println("크롤링 중 오류 발생: " + e.getMessage());
//        }
//    }


}
