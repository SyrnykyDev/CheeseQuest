package com.mykyda.api.Controller;

import com.mykyda.api.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quests")
@RequiredArgsConstructor
public class QuestsController {

    private final QuestService questService;

    @GetMapping("/all")
    public ResponseEntity<?> getQuests() {
        return questService.findAll();
    }

}
