package com.erkindilekci.jobapp.controller;

import com.erkindilekci.jobapp.model.Review;
import com.erkindilekci.jobapp.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.findAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.findReviewById(companyId, reviewId);
        return review == null
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        return reviewService.createReview(companyId, review)
                ? new ResponseEntity<>("Review created successfully", HttpStatus.CREATED)
                : new ResponseEntity<>("No company found with company id: " + companyId, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        return reviewService.updateReview(companyId, reviewId, review)
                ? new ResponseEntity<>("Review updated successfully", HttpStatus.OK)
                : new ResponseEntity<>("No review found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return reviewService.deleteReviewById(companyId, reviewId)
                ? new ResponseEntity<>("Review deleted successfully", HttpStatus.OK)
                : new ResponseEntity<>("No review found", HttpStatus.NOT_FOUND);
    }
}
