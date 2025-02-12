package com.mykyda.api.database.entity;


import com.mykyda.security.database.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "author")
public class Author {
    @Id
    private Long idUser;
    private String username;
    private int sumScore;
    private int sumQuest;

}
