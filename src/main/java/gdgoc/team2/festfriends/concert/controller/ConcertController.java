package gdgoc.team2.festfriends.concert.controller;

import gdgoc.team2.festfriends.concert.dto.ConcertResponse;
import gdgoc.team2.festfriends.concert.service.ConcertService;
import gdgoc.team2.festfriends.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @Operation(summary = "전체 공연 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ConcertResponse>>> getConcerts() {
        List<ConcertResponse> concertResponseList = concertService.getConcerts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ApiResponse.<List<ConcertResponse>>builder()
                                .success(true)
                                .message(null)
                                .data(concertResponseList)
                                .build()
                );
    }

    @Operation(summary = "카테고리별 공연 조회")
    @GetMapping("/{category}")
    public ResponseEntity<ApiResponse<List<ConcertResponse>>> getConcertsByCategory(@PathVariable(required = false) String category) {
        List<ConcertResponse> concertResponseList = concertService.getConcertsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ApiResponse.<List<ConcertResponse>>builder()
                                .success(true)
                                .message(null)
                                .data(concertResponseList)
                                .build()
                );
    }


    @GetMapping("/crawling")
    public String crawling() {
        concertService.crawlingConcerts();
        return "<h1>done</h1>";
    }


}
