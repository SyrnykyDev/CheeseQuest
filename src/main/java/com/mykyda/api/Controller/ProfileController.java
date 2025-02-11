package com.mykyda.api.Controller;

import com.mykyda.api.service.QuestService;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        return userService.findById(id);
    }

//    @GetMapping("/{id}/history")
//    public ResponseEntity<?> getProfileHistory(@PathVariable Long id) {
//        return userService.findById(id);
//    }
}
