package gdgoc.team2.festfriends.concert.service;

import gdgoc.team2.festfriends.concert.dto.ConcertResponse;
import gdgoc.team2.festfriends.concert.entity.Concert;
import gdgoc.team2.festfriends.concert.repository.ConcertRepository;
import gdgoc.team2.festfriends.global.dto.ApiResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConcertService {

    ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void crawlingConcerts() {

        try {
            String url = "https://tickets.interpark.com/contents/genre/concert";
            Document doc = Jsoup.connect(url).get();

            Elements concertItems = doc.select("a.TicketItem_ticketItem__H51Vs");

            for (Element item : concertItems) {

                String name = item.select("li.TicketItem_goodsName__Ju76j").text();
                String location = item.select("li.TicketItem_placeName__ls_9C").text();
                String period = item.select("li.TicketItem_playDate__5ePr2").text();
                String imageUrl = "https://tickets.interpark.com" + item.select("img.TicketItem_image__U6xq6").attr("src");

                Concert concert = Concert.builder()
                        .imageUrl(imageUrl)
                        .name(name)
                        .location(location)
                        .period(period)
                        .build();

                concertRepository.save(concert);
            }
        } catch (IOException e) {
            System.err.println("크롤링 중 오류 발생: " + e.getMessage());
        }
    }

    public ApiResponse<List<ConcertResponse>> getAllConcerts(){
        ApiResponse<List<ConcertResponse>> apiResponse = new ApiResponse<>();
        List<Concert> concerts = concertRepository.findAll();
        List<ConcertResponse> concertResponses = new ArrayList<>();

        for (Concert concert : concerts) {
            ConcertResponse concertResponse = new ConcertResponse(
                    concert.getId(),
                    concert.getName(),
                    concert.getLocation(),
                    concert.getPeriod(),
                    concert.getImageUrl()
            );

            concertResponses.add(concertResponse);
        }

        apiResponse.setSuccess(true);
        apiResponse.setData(concertResponses);

        return apiResponse;
    }
}
