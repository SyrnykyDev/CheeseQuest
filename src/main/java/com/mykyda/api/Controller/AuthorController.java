package com.mykyda.api.controller;

import com.mykyda.api.service.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorsService authorService;

    @GetMapping
    public ResponseEntity<?> getAuthors() {
        return authorService.findAllSortedByScore();
    }

}
