package com.mykyda.security.controller;

import com.mykyda.security.database.entity.User;
import com.mykyda.security.dto.AuthRespDto;
import com.mykyda.security.dto.UserCreateDto;
import com.mykyda.security.service.JwtService;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/auth/login")
@RequiredArgsConstructor
public class LoginController {

    public final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> defaultLogin(@RequestBody UserCreateDto userCreateDto){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(userCreateDto.getEmail(), userCreateDto.getPassword()));
        UserDetails user = userService.loadUserByUsername(userCreateDto.getEmail());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthRespDto(token));
    }
    @GetMapping()
    public String getLogin(){
        return "login";
    }
}
