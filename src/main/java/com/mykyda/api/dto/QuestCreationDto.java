package com.mykyda.api.dto;

import com.mykyda.api.database.entity.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Value
@FieldNameConstants
@Getter
@Setter
public class QuestCreationDto {

    String name;

    String description;

    int rating = 0;

    List<Task> tasks;
}