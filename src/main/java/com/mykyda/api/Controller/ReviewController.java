package com.mykyda.api.controller;

import com.mykyda.api.database.entity.Review;
import com.mykyda.api.dto.ReviewDemoDto;
import com.mykyda.api.dto.ReviewDto;
import com.mykyda.api.service.ReviewService;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping("/createReview")
    public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto, Principal principal) {
        var user = userService.findByEmail(principal.getName());
        return reviewService.createReview(reviewDto,user.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewDemoDto>> getReviewsByQuestId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByQuestId(id));
    }
}