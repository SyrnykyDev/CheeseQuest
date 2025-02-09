package com.mykyda.vitalik.Repositiry;

import com.mykyda.vitalik.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
