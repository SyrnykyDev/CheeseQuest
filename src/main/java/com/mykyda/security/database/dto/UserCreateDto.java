package com.mykyda.security.database.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@Getter
@Setter
public class UserCreateDto {

    String username;

    String password;

    String email;
}