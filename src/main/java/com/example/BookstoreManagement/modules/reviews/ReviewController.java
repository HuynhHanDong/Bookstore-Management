package com.example.BookstoreManagement.modules.reviews;

import com.example.BookstoreManagement.database.entities.ReviewEntity;
import com.example.BookstoreManagement.modules.reviews.dto.ReviewRequestDTO;
import com.example.BookstoreManagement.modules.reviews.dto.ReviewResponseDTO;
import com.example.BookstoreManagement.modules.reviews.dto.SentimentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/")
    public ResponseEntity addReview(@RequestBody @Valid ReviewRequestDTO dto) {
        reviewService.addReview(dto);
        return new ResponseEntity("Review added", HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity("Review deleted", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity listReview() {
         List<ReviewEntity> reviewList = reviewService.listReview();
        return new ResponseEntity(ReviewResponseDTO.fromEntities(reviewList), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity getReview(@PathVariable Integer reviewId) {
        ReviewEntity review = reviewService.getReview(reviewId);
        return new ResponseEntity(ReviewResponseDTO.fromEntity(review), HttpStatus.OK);
    }

    @GetMapping("/rate/{rate}")
    public ResponseEntity findByRate(
            @PathVariable Integer rate,
            @RequestParam(required = false, defaultValue = "createAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order
    ) {
        List<ReviewEntity> reviewList = reviewService.findByRate(rate, sortBy, order);
        return new ResponseEntity(ReviewResponseDTO.fromEntities(reviewList), HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity findByBookId(
            @PathVariable Integer bookId,
            @RequestParam(required = false, defaultValue = "rate") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order
    ) {
        List<ReviewEntity> reviewList = reviewService.findByRate(bookId, sortBy, order);
        return new ResponseEntity(ReviewResponseDTO.fromEntities(reviewList), HttpStatus.OK);
    }

    @PostMapping("/{reviewId}/analyze")
    public ResponseEntity analyzeReviewSentiment(@PathVariable Integer reviewId) {
        SentimentResponseDTO sentimentResponse = reviewService.analyzeReviewSentiment(reviewId);
        return new ResponseEntity(sentimentResponse, HttpStatus.OK);
    }
}
