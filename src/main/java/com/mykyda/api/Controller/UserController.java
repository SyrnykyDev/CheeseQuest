package com.mykyda.api.controller;

import com.mykyda.api.dto.ProfileEditDto;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getProfile(Principal principal) {
        return userService.findByPrincipal(principal);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> postProfileEdit(@RequestPart MultipartFile file, @RequestPart String username, Principal principal) {
        return userService.save(file, username, principal);
    }
}
