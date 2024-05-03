package com.erkindilekci.jobapp.service.impl;

import com.erkindilekci.jobapp.model.Company;
import com.erkindilekci.jobapp.model.Review;
import com.erkindilekci.jobapp.repository.ReviewRepository;
import com.erkindilekci.jobapp.service.CompanyService;
import com.erkindilekci.jobapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review findReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company == null) return false;
        review.setCompany(company);
        reviewRepository.save(review);
        return true;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.findCompanyById(companyId);
        if (company == null || !reviewRepository.existsById(reviewId)) return false;
        updatedReview.setCompany(company);
        updatedReview.setId(reviewId);
        reviewRepository.save(updatedReview);
        return true;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.findCompanyById(companyId) == null || !reviewRepository.existsById(reviewId)) return false;
        Review review = findReviewById(companyId, reviewId);
        reviewRepository.deleteById(review.getId());
        return true;
    }
}
