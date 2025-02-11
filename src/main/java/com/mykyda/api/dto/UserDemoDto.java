package com.mykyda.api.dto;

import com.mykyda.api.database.entity.Task;
import com.mykyda.security.database.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Value
@FieldNameConstants
@Getter
@Setter
public class UserDemoDto {

    String avatar;

    String username;

    String email;

    Role role;
}