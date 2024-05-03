package com.erkindilekci.jobapp.service;

import com.erkindilekci.jobapp.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findAllReviews(Long companyId);

    Review findReviewById(Long companyId, Long reviewId);

    boolean createReview(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReviewById(Long companyId, Long reviewId);
}
