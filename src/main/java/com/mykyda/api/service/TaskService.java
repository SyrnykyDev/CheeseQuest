package com.mykyda.api.service;

import com.mykyda.api.database.entity.Task;
import com.mykyda.api.database.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAllByQuest(Long id) {
        return taskRepository.findAllByQuestId(id);
    }

    public ResponseEntity<?> saveAllTasks(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            taskRepository.saveAll(tasks);
        }
        return new ResponseEntity<>(Collections.singletonMap("message", "successfully created"), HttpStatus.CREATED);
    }
}
