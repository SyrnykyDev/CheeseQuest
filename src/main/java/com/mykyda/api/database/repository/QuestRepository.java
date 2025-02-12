package com.mykyda.api.database.repository;

import com.mykyda.api.database.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestRepository extends JpaRepository<Quest,Long> {
    Optional<Quest> findById(Long id);

    List<Quest> findAllByAuthorId(Long id);
}
