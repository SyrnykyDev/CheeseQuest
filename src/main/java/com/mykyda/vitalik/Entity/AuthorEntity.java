package com.mykyda.vitalik.Entity;


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
public class AuthorEntity {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "sum_score")
    private int sumScore;
    @Column(name = "sum_quest")
    private int sumQuest;

}
