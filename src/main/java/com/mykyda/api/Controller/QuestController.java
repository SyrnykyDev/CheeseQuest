package com.mykyda.api.controller;

import com.mykyda.api.database.entity.Author;
import com.mykyda.api.dto.TaskAnswerDto;
import com.mykyda.api.service.AuthorsService;
import com.mykyda.api.service.QuestService;
import com.mykyda.api.service.ReviewService;
import com.mykyda.api.service.TaskService;
import com.mykyda.api.dto.QuestCreationDto;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/quest")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    private final TaskService taskService;

    private final AuthorsService authorService;

    private final UserService userService;

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuest(@PathVariable(name = "id") Long id,Principal principal) {
        var quest = questService.findObjectById(id);
        var reviews = reviewService.getReviewsByQuestId(id);
        var user = userService.findByEmail(principal.getName());
        var author = authorService.findById(user.getId());
        var isAuthor = false;
        if (principal != null) {
            if (author.getIdUser() == quest.getAuthorId()) {
                isAuthor = true;
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("quest",quest);
        response.put("reviews",reviews);
        response.put("author",author);
        response.put("isAuthor",isAuthor);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}/start")
    public ResponseEntity<?> start(@PathVariable(name = "id") Long id) {
        var tasks = taskService.findAllByQuest(id);
        if (!tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message","can`t start quest with no tasks"), HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/{id}/end")
//    public ResponseEntity<?> end(@RequestBody List<TaskAnswerDto> answers) {
//        var tasks = taskService.findAllByQuest(id);
//        if (!tasks.isEmpty()) {
//            return new ResponseEntity<>(tasks, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Collections.singletonMap("message","can`t start quest with no tasks"), HttpStatus.NOT_FOUND);
//        }
//    }

//    @PostMapping("/{id}/start")
//    public ResponseEntity<?> start(@PathVariable(name = "id") Long id) {
//        var tasks = taskService.findAllByQuest(id);
//        if (!tasks.isEmpty()) {
//            var indexes = new ArrayList<Long>();
//            for (var i : tasks) {
//                indexes.add(i.getId());
//            }
//            return new ResponseEntity<>(indexes, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Collections.singletonMap("message","can`t start quest with no tasks"), HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping("/edit/{id}")
//    public Map<String, Object> getQuestToEdit(@PathVariable(name = "id") Long id) {
//        var quest = questService.findById(id);
//        var tasks = taskService.findAllByQuest(id);
//        return Map.of(
//                "tasks", tasks,
//                "quest", quest);
//    }

//    @PostMapping("/edit/{id}")
//    public ResponseEntity<?> postQuestToEdit(@RequestBody QuestEditDto qeDto) {
//        questService.update(qeDto);
//        var tasks = qeDto.getTasks();
//        return taskService.saveAllTasks(tasks);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createQuest(@RequestParam String name, @RequestParam String description, @RequestParam(required = false) MultipartFile media, @RequestParam(required = false) Integer timeLimit, Principal principal) {
        var user = userService.findByEmail(principal.getName());
        var author = (Author) authorService.checkAndCreate(user.getId()).getBody();
        authorService.incrementProjectAmount(author);
        var savedQuest = questService.create(name,description,media,timeLimit,author.getIdUser());
        return new ResponseEntity<>(savedQuest.getId(),HttpStatus.OK);
    }


}
