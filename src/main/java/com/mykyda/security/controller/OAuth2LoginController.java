package com.mykyda.security.controller;

import com.mykyda.security.service.JwtService;
import com.mykyda.security.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth/oauth2")
@RequiredArgsConstructor
public class OAuth2LoginController {

    private final JwtService jwtService;

    private final UserService userService;

    @GetMapping("/success")
    public ResponseEntity<?> onOAuth2LoginSuccess(HttpServletResponse response) {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = authenticationToken.getPrincipal().getName();
        String jwt = jwtService.generateToken(email);
        Cookie cookie = new Cookie("accessToken", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
