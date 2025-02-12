package com.mykyda.api.service;

import com.mykyda.api.database.entity.Review;
import com.mykyda.api.database.repository.QuestRepository;
import com.mykyda.api.database.repository.ReviewRepository;
import com.mykyda.api.dto.ReviewDto;
import com.mykyda.security.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;

    public ResponseEntity<?> createReview(ReviewDto reviewDto, Long userId) {
        Review review = Review.builder()
                .user(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")))
                .questId(reviewDto.getQuestId())
                .text(reviewDto.getMessage())
                .build();
        return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.OK);
    }
    public List<Review> getReviewsByQuestId(Long questId) {
        return reviewRepository.findAllByQuestId(questId).orElseThrow(() -> new RuntimeException("No reviews found for this quest"));
    }
}