package com.mykyda.security.controller;

import com.mykyda.security.database.entity.User;
import com.mykyda.security.dto.AuthRespDto;
import com.mykyda.security.dto.LoginDto;
import com.mykyda.security.dto.UserCreateDto;
import com.mykyda.security.service.JwtService;
import com.mykyda.security.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/api/auth/login")
@RequiredArgsConstructor
public class LoginController {

    public final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    @PostMapping()
    public ResponseEntity<?> defaultLogin(@RequestBody LoginDto loginDto, HttpServletResponse response){
        var user = userService.findByEmail(loginDto.getEmail());
        if (user != null){
            authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            setCookie(user,response);
        }else {
            throw new RuntimeException("no user found");
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping()
    public String getLogin(){
        return "login";
    }

    private void setCookie(User user, HttpServletResponse response) {
        var token = jwtService.generateToken(user);
        Cookie cookie = createCookie(token);
        response.addCookie(cookie);
    }


    public Cookie createCookie(String jwt) {
        Cookie cookie = new Cookie("accessToken", jwt);
        cookie.setMaxAge(24_192_000);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        return cookie;
    }
}
