package com.mykyda.api.database.repository;

import java.util.List;
import java.util.Optional;

import com.mykyda.api.database.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findAllByQuestId(Long questId);

}