package com.mykyda.api.service;

import com.mykyda.api.database.entity.Quest;
import com.mykyda.api.database.repository.QuestRepository;
import com.mykyda.api.dto.QuestCreationDto;
import com.mykyda.api.dto.QuestEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;

    public ResponseEntity<?> findById(Long id) {
        var quest = questRepository.findById(id).get();
        if (quest == null){
            return new ResponseEntity<>(Collections.singletonMap("message","no quest with such id"),HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(quest,HttpStatus.OK);
        }
    }

    public Quest create(QuestCreationDto qcDto) {
        var quest = Quest.builder()
                .authorId(qcDto.getAuthorId())
                .description(qcDto.getDescription())
                .name(qcDto.getName())
                .rating(qcDto.getRating())
                .timeLimit(qcDto.getTimeLimit()).build();
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

    public ResponseEntity<?> findAll(){
        var quests = questRepository.findAll();
        if (!quests.isEmpty()){
            return new ResponseEntity<>(quests,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message","no existing quests yet"),HttpStatus.NOT_FOUND);
        }
    }
}
