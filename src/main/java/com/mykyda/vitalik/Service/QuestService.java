package com.mykyda.vitalik.Service;

import com.mykyda.vitalik.Entity.QuestEntity;
import com.mykyda.vitalik.Repositiry.QuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestService {
    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public QuestEntity getQuest(int id) {
        return questRepository.findById(id).orElse(null);
    }
}
