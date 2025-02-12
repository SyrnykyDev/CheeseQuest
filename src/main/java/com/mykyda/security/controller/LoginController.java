package com.mykyda.security.controller;

import com.mykyda.security.database.entity.User;
import com.mykyda.security.dto.AuthRespDto;
import com.mykyda.security.dto.LoginDto;
import com.mykyda.security.dto.UserCreateDto;
import com.mykyda.security.service.JwtService;
import com.mykyda.security.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    public final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        var user = userService.findByEmail(loginDto.getEmail());
        if (user != null) {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            var token = jwtService.generateToken(user);
            setCookie(token, response);
            return ResponseEntity.ok(token);
        } else {
            throw new RuntimeException("no user found");
        }
    }

    private void setCookie(String token, HttpServletResponse response) {
//        var token = jwtService.generateToken(user);
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

//    @PostMapping()
//    public ResponseEntity<?> checkToken(HttpServletRequest request){
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            String email = jwtService.extractUsername(token);
//            if (email != null && jwtService.validateToken(token)) {
//                return ResponseEntity.ok("Token is valid");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header missing or incorrect");
//        }
//    }

    @GetMapping()
    public ResponseEntity<?> getCurrentAccount(Principal principal) {
        if (principal.getName() != null) {
            return ResponseEntity.ok(Collections.singletonMap("validated",true));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("validated",false));
        }
    }
}
