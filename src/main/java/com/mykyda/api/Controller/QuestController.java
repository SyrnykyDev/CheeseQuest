package com.mykyda.api.controller;

import com.mykyda.api.database.entity.Quest;
import com.mykyda.api.service.QuestService;
import com.mykyda.api.service.TaskService;
import com.mykyda.api.dto.QuestCreationDto;
import com.mykyda.api.dto.QuestEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/quest")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    private final TaskService taskService;

    @GetMapping("/{id}")
    public Quest getQuest(@PathVariable(name = "id") Long id) {
        return questService.findById(id);
    }

    @GetMapping("/edit/{id}")
    public Map<String, Object> getQuestToEdit(@PathVariable(name = "id") Long id) {
        var quest = questService.findById(id);
        var tasks = taskService.findAllByQuest(id);
        return Map.of(
                "tasks", tasks,
                "quest", quest);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> postQuestToEdit(@RequestBody QuestEditDto qeDto) {
        questService.update(qeDto);
        var tasks = qeDto.getTasks();
        return taskService.saveAllTasks(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createQuest(@RequestBody QuestCreationDto qcDto) {
        var savedQuest = questService.create(qcDto);
        var tasks = qcDto.getTasks();
        if (!tasks.isEmpty()) {
            tasks.forEach(task -> task.setQuestId(savedQuest.getId()));
        }
        return taskService.saveAllTasks(tasks);
    }
}
