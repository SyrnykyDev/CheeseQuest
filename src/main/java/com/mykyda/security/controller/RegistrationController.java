package com.mykyda.security.controller;

import com.mykyda.security.database.entity.User;
import com.mykyda.security.dto.AuthRespDto;
import com.mykyda.security.dto.UserCreateDto;
import com.mykyda.security.service.JwtService;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/auth/registration")
@RequiredArgsConstructor
public class RegistrationController {

    public final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> defaultRegistration(@RequestBody UserCreateDto userCreateDto){
        User user = userService.register(userCreateDto.getUsername(), userCreateDto.getEmail(), userCreateDto.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping()
    public String getRegistration(){
        return "registration";
    }
}
