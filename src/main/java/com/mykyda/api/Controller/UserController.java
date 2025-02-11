package com.mykyda.api.Controller;

import com.mykyda.api.service.QuestService;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/{id}/history")
//    public ResponseEntity<?> getProfileHistory(@PathVariable Long id) {
//        return userService.findById(id);
//    }
}
