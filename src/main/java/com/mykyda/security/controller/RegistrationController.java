package com.mykyda.security.controller;

import com.mykyda.security.database.dto.UserCreateDto;
import com.mykyda.security.database.entity.User;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/registration")
@RequiredArgsConstructor
public class RegistrationController {

    public final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> defaultRegistration(@RequestBody UserCreateDto userCreateDto){
        var result = userService.register(userCreateDto.getUsername(),userCreateDto.getEmail(),userCreateDto.getPassword());
        System.out.println(result);
        return result;
    }
}
