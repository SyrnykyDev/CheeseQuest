package com.mykyda.vitalik.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String media;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_id")
    private QuestEntity questId;
    private String answer;
    private String type;
    private int screenWidth;
    private int screenHeight;
    @Column(name = "objects_position")
    private String objectsPosition;
}
