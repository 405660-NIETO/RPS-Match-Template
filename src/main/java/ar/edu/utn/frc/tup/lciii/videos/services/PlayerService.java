package ar.edu.utn.frc.tup.lciii.videos.services;

import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface PlayerService {
    Player getPlayerById(Long id);
    Player savePlayer(Player player);
    Player getPlayerByUserNameAndPassword(String userName, String password);
    Player getPlayerByEmailAndPassword(String email, String password);
    Player getPlayerByUserNameOrEmailAndPassword(String identity, String password);

    void updateLastLogin(Long id, LocalDateTime lastLogin);
}
