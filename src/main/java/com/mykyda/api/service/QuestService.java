package com.mykyda.api.service;

import com.mykyda.api.database.entity.Quest;
import com.mykyda.api.database.repository.QuestRepository;
import com.mykyda.api.dto.QuestCreationDto;
import com.mykyda.api.dto.QuestDemoDto;
import com.mykyda.api.dto.QuestEditDto;
import com.mykyda.api.dto.UserDemoDto;
import com.mykyda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;

    private final UserService userService;

    private final MediaService mediaService;

    public QuestDemoDto findById(Long id) {
        var quest = questRepository.findById(id).orElse(null);
//        if (quest == null) {
//            return new ResponseEntity<>(Collections.singletonMap("message", "no quest with such id"), HttpStatus.NOT_FOUND);
//        } else {
        return new QuestDemoDto(quest.getId(), quest.getName(), quest.getDescription(), quest.getRating(), (UserDemoDto) userService.findById(quest.getAuthorId()).getBody());
//            return new ResponseEntity<>(res, HttpStatus.OK);
//        }
    }

    public Quest findObjectById(Long id) {
        return questRepository.findById(id).orElse(null);
    }

    public Quest create(String name, String desc, MultipartFile media, Integer timeLimit, Long authorId) {
        var savedMedia = mediaService.uploadQuestMedia(media);
        var quest = Quest.builder()
                .authorId(authorId)
                .description(desc)
                .name(name)
                .media(savedMedia)
                .rating(0)
                .timeLimit(timeLimit)
                .build();
        return questRepository.save(quest);
    }

    public void update(QuestEditDto qeDto) {
        var quest = Quest.builder()
                .id(qeDto.getId())
                .authorId(qeDto.getAuthorId())
                .description(qeDto.getDescription())
                .name(qeDto.getName())
                .rating(qeDto.getRating())
                .timeLimit(qeDto.getTimeLimit()).build();
        questRepository.save(quest);
        new ResponseEntity<>(Collections.singletonMap("message", "successful update"), HttpStatus.OK);
    }

    public ResponseEntity<?> findAll() {
        var quests = questRepository.findAll();
        if (!quests.isEmpty()) {
            return new ResponseEntity<>(quests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message", "no existing quests yet"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findAllByAuthorId(Long id) {
        var quests = questRepository.findAllByAuthorId(id);
        if (!quests.isEmpty()) {
            return new ResponseEntity<>(quests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message", "no quests found"), HttpStatus.NOT_FOUND);
        }
    }
}
