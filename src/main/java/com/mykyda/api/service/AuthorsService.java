package com.mykyda.api.service;

import com.mykyda.api.database.entity.Author;
import com.mykyda.api.database.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthorsService {

    private final AuthorRepository authorRepository;

    public ResponseEntity<?> findAllSortedByScore() {
        var authors = authorRepository.findAllByOrderBySumScoreDesc();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "no authors yet"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> checkAndCreate(Long id) {
        var author = authorRepository.findById(id).get();
        if (author == null) {
            author = authorRepository.save(Author.builder()
                    .idUser(id)
                    .sumQuest(0)
                    .sumScore(0)
                    .build());
            return new ResponseEntity<>(author, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
    }

    public void incrementProjectAmount(Author author){
        author.setSumQuest(author.getSumQuest()+1);
    }

    public Author findById(Long id){
        return authorRepository.findById(id).orElse(null);
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }
}
