package com.mykyda.security.database.repository;

import com.mykyda.security.database.entity.User;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    void delete(User entity);

    Optional<User> findByUsername(String username);
}
