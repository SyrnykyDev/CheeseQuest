package com.mykyda.api.database.repository;

import com.mykyda.api.database.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findAllByOrderBySumScoreDesc();

    Optional<Author> findById(Long id);
}
