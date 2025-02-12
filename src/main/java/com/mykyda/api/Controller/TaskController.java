package com.mykyda.api.controller;

import com.mykyda.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuest(@RequestParam Long questId, @RequestParam MultipartFile media, @RequestParam String type, @RequestParam String question, @RequestParam String answer) {
        taskService.saveTask(questId,media,question,answer,type);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
