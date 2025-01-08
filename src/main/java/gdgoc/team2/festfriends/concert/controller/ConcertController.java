package gdgoc.team2.festfriends.concert.controller;

import gdgoc.team2.festfriends.concert.dto.ConcertResponse;
import gdgoc.team2.festfriends.concert.service.ConcertService;
import gdgoc.team2.festfriends.global.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concert")
public class ConcertController {

    ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping("/crawling")
    public String crawling() {
        concertService.crawlingConcerts();
        return "<h1>done</h1>";
    }

    @GetMapping("/contents")
    public ApiResponse<List<ConcertResponse>> contents() {
        try{
            List<ConcertResponse> concertResponses = concertService.getAllConcerts();
            return ApiResponse.<List<ConcertResponse>>builder()
                    .success(true)
                    .message(null)
                    .data(concertResponses)
                    .build();

        }catch (Exception e){
            return ApiResponse.<List<ConcertResponse>>builder()
                    .success(false)
                    .message(null)
                    .data(null)
                    .build();
        }
    }
}
