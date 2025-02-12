package com.mykyda.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@Getter
@Setter
public class LoginDto {

    String password;

    String email;
}