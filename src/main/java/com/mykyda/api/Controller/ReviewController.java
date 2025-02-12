package com.mykyda.api.controller;

import com.mykyda.api.database.entity.Review;
import com.mykyda.api.dto.ReviewDto;
import com.mykyda.api.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/createReview")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/{questId}")
    public ResponseEntity<List<Review>> getReviewsByQuestId(@PathVariable Long questId) {
        return ResponseEntity.ok(reviewService.getReviewsByQuestId(questId));
    }
}