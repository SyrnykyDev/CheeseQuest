package com.mykyda.api.service;

import com.mykyda.api.database.entity.Task;
import com.mykyda.api.database.repository.TaskRepository;
import com.mykyda.api.dto.TaskCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private final MediaService mediaService;

    public List<Task> findAllByQuest(Long id) {
        return taskRepository.findAllByQuestId(id);
    }


//    public ResponseEntity<?> saveAllTasks(List<TaskCreationDto> dtoTasks, Long authorId, Long questId) {
//        List<Task> tasks = new ArrayList<>();
//        if (!dtoTasks.isEmpty()) {
//            for (var task : dtoTasks) {
//                var mediaUrl = mediaService.uploadTaskMedia(task.getMedia());
//                tasks.add(Task.builder()
//                        .answer(task.getAnswer())
//                        .media(mediaUrl)
//                        .questId(questId)
//                        .type(task.getType())
//                        .build());
//            }
//            taskRepository.saveAll(tasks);
//        }
//        return new ResponseEntity<>(Collections.singletonMap("message", "successfully created"), HttpStatus.CREATED);
//    }

    public Task saveTask(Long questId, MultipartFile media, String type, String question, String answer) {
        var savedMedia = mediaService.uploadTaskMedia(media);
        var task = Task.builder()
                .questId(questId)
                .type(type)
                .media(savedMedia)
                .answer(answer)
                .question(question)
                .build();
        return taskRepository.save(task);
    }

}
