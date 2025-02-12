package com.mykyda.api.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long userId;
    private Long questId;
    private String text;
}