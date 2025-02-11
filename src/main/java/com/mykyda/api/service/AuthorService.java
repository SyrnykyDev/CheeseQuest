package com.mykyda.api.service;

import com.mykyda.api.database.entity.Quest;
import com.mykyda.api.database.repository.AuthorRepository;
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
public class AuthorService {

    private final AuthorRepository authorRepository;

    public ResponseEntity<?> findAllSortedByScore(){
        var authors = authorRepository.findAllByOrderBySumScoreDesc();
        if (authors.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message","no authors yet"),HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(authors,HttpStatus.OK);
        }
    }
}
