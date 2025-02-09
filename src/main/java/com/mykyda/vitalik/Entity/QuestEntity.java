package com.mykyda.vitalik.Entity;

import com.mykyda.security.database.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "quest")
public class QuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int timeLimit;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorId;
    private int rating;
    @OneToMany(mappedBy = "questId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskEntity> tasks;
}
