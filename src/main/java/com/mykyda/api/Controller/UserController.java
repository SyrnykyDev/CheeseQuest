package com.mykyda.api.controller;

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

    //ToDo:type validation
    @PostMapping("/edit")
    public ResponseEntity<?> postProfileEdit(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "username", required = false) String username, Principal principal) {
        return userService.save(file, username, principal);
    }

}
