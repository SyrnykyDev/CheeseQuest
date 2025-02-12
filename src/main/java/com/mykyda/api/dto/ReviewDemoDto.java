package com.mykyda.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@Getter
@Setter
public class ReviewDemoDto {

    String username;

    String message;

    String avatar;
}