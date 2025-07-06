package com.freeplayzone.FreePlayZone.domain.gameLibrary;

import com.freeplayzone.FreePlayZone.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Game_Library", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "name"})
})
@AllArgsConstructor
@NoArgsConstructor
public class GameLibrary
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String imageUrl;

    @Getter
    @Setter
    private String mainGenre;

    @Getter
    @Setter
    private String releaseDate;

    @Getter
    @Setter
    private boolean isFree;

}