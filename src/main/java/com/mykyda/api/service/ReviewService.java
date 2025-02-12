package com.mykyda.api.service;

import com.mykyda.api.database.entity.Review;
import com.mykyda.api.database.repository.QuestRepository;
import com.mykyda.api.database.repository.ReviewRepository;
import com.mykyda.api.dto.ReviewDto;
import com.mykyda.security.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                .user(userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")))
                .quest(questRepository.findById(reviewDto.getQuestId()).orElseThrow(() -> new RuntimeException("Quest not found")))
                .text(reviewDto.getText())
                .build();
        reviewRepository.save(review);
        return reviewDto;
    }
    public List<Review> getReviewsByQuestId(Long questId) {
        return reviewRepository.findAllByQuestId(questId).orElseThrow(() -> new RuntimeException("No reviews found for this quest"));
    }
}