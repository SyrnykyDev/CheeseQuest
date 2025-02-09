package com.mykyda.vitalik.Controller;

import com.mykyda.vitalik.Entity.QuestEntity;
import com.mykyda.vitalik.Service.QuestService;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quest")
public class ControllerQuest {

    private final QuestService questService;

    public ControllerQuest(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping("/{id}")
    public QuestEntity test(@PathVariable(name = "id") int id) {
        return questService.getQuest(id);
    }
}
