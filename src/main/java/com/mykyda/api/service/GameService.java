//package com.mykyda.api.service;
//
//import com.mykyda.api.database.entity.Game;
//import com.mykyda.api.database.entity.Task;
//import com.mykyda.api.database.repository.GameRepository;
//import com.mykyda.api.database.repository.TaskRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class GameService {
//    private final GameRepository gameRepository;
//
//    public ResponseEntity<?> findAll(){
//        var games = gameRepository.findAll();
//        if (!games.isEmpty()){
//            return new ResponseEntity<>(games,HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Collections.singletonMap("message","there are no games yet"),HttpStatus.NOT_FOUND);
//        }
//    }
//}
