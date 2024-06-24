package ar.edu.utn.frc.tup.lciii.videos.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.videos.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {
    //Optional<GameEntity> findGameEntityById (Long id);
    //Optional<GameEntity> getGameEntityById (Long id);
    //Optional<GameEntity> findById(Long id);
    //Optional<GameEntity> getAllById(Long id);
}
