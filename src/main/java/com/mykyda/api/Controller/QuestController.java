package com.mykyda.api.controller;

import com.mykyda.api.database.entity.Author;
import com.mykyda.api.database.entity.Quest;
import com.mykyda.api.service.AuthorsService;
import com.mykyda.api.service.QuestService;
import com.mykyda.api.service.TaskService;
import com.mykyda.api.dto.QuestCreationDto;
import com.mykyda.api.dto.QuestEditDto;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/quest")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    private final TaskService taskService;

    private final AuthorsService authorService;

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuest(@PathVariable(name = "id") Long id) {
        return questService.findById(id);
    }

    @GetMapping("/{id}/start")
    public ResponseEntity<?> start(@PathVariable(name = "id") Long id) {
        var tasks = taskService.findAllByQuest(id);
        if (!tasks.isEmpty()) {
            var indexes = new ArrayList<Long>();
            for (var i : tasks) {
                indexes.add(i.getId());
            }
            return new ResponseEntity<>(indexes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message","can`t start quest with no tasks"), HttpStatus.NOT_FOUND);
        }
    }

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
    public ResponseEntity<?> createQuest(@RequestBody QuestCreationDto qcDto, Principal principal) {
        var user = userService.findByEmail(principal.getName());
        var author = (Author) authorService.checkAndCreate(user.getId()).getBody();
        authorService.incrementProjectAmount(author);
        var savedQuest = questService.create(qcDto,principal,author.getIdUser());
        var tasks = qcDto.getTasks();
        if (!tasks.isEmpty()) {
            taskService.saveAllTasks(tasks,author.getIdUser(),savedQuest.getId());
        }
        return new ResponseEntity<>(Collections.singletonMap("message","quest created"),HttpStatus.OK);
    }


}
