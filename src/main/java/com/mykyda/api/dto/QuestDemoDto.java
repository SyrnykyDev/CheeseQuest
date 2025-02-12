package com.mykyda.api.dto;

import com.mykyda.security.database.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Value
@FieldNameConstants
@Getter
@Setter
public class QuestDemoDto {
    Long id;

    String name;

    String description;

    int rating;

    User user;
}