package com.mykyda.api.dto;

import com.mykyda.api.database.entity.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Value
@FieldNameConstants
@Getter
@Setter
public class TaskCreationDto {

    MultipartFile media;

    String answer;

    String type;

    String question;
}