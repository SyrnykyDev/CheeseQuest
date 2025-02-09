package com.mykyda.vitalik.Repositiry;

import com.mykyda.vitalik.Entity.QuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<QuestEntity, Integer> {
}
