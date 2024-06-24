package ar.edu.utn.frc.tup.lciii.videos.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.videos.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {

    /*@Query("SELECT m FROM MatchEntity m WHERE m.player.id = :playerId")
    Optional<List<Match>> getAllByPlayer(@Param("playerId") Long playerId);*/

    @Query("SELECT m FROM MatchEntity m WHERE m.player.id = :playerId")
    Optional<List<MatchEntity>> getAllByPlayerId(@Param("playerId") Long playerId);

    //Optional<List<MatchEntity>> getAllByPlayerId(Long playerId);
}
