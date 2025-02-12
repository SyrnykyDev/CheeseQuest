//package com.mykyda.api.controller;
//
//import com.mykyda.api.database.entity.Game;
//import com.mykyda.api.service.GameService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/game")
//@RequiredArgsConstructor
//public class GameController {
//
//    private final GameService gameService;
//
//
//
//    @GetMapping
//    public List<Game> getOpenGames() {
//        return gameService.getOpenGames();
//    }
//
//    @PostMapping
//    public Game createGame(@RequestParam Long creatorId) {
//        return gameService.createGame(creatorId);
//    }
//
//    @PostMapping("/{id}/join")
//    public void joinGame(@PathVariable Long id, @RequestParam String username) {
//        gameService.joinGame(id, username);
//    }
//
//    @PostMapping("/{id}/start")
//    public void startGame(@PathVariable Long id) {
//        gameService.startGame(id);
//    }
//}
