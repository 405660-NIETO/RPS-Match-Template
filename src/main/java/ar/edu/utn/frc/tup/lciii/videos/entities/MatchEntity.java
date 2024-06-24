package ar.edu.utn.frc.tup.lciii.videos.entities;

import ar.edu.utn.frc.tup.lciii.videos.models.MatchStatus;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "matches")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "game_id")
    @ManyToOne
    private GameEntity game;

    @JoinColumn(name = "player_id")
    @ManyToOne
    private PlayerEntity player;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;


    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MatchStatus status;

}
