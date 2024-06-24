package ar.edu.utn.frc.tup.lciii.videos.services;

import ar.edu.utn.frc.tup.lciii.videos.models.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    Game getGameById(Long id);
}
