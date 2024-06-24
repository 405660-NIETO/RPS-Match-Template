package ar.edu.utn.frc.tup.lciii.videos.services.impl;

import ar.edu.utn.frc.tup.lciii.videos.dtos.login.Credential;
import ar.edu.utn.frc.tup.lciii.videos.dtos.login.CredentialV2;
import ar.edu.utn.frc.tup.lciii.videos.dtos.login.EmailIdentity;
import ar.edu.utn.frc.tup.lciii.videos.dtos.login.UserNameIdentity;
import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.services.LoginService;
import ar.edu.utn.frc.tup.lciii.videos.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private PlayerService playerService;

    @Override
    public Player login(Credential credential) {
        if (credential.getIdentity() instanceof UserNameIdentity) {
            return loginWithIdentity((UserNameIdentity) credential.getIdentity(), credential.getPassword());
        } else {
            return loginWithIdentity((EmailIdentity) credential.getIdentity(), credential.getPassword());
        }
    }

    @Override
    public Player login(CredentialV2 credential) {
        Player player = playerService.getPlayerByUserNameOrEmailAndPassword(credential.getIdentity(), credential.getPassword());
        playerService.updateLastLogin(player.getId(), LocalDateTime.now());
        return player;
    }

    private Player loginWithIdentity(UserNameIdentity userNameIdentity, String password) {
        Player player = playerService.getPlayerByUserNameAndPassword(userNameIdentity.getUserName(), password);
        playerService.updateLastLogin(player.getId(), LocalDateTime.now());
        return player;
    }

    private Player loginWithIdentity(EmailIdentity emailIdentity, String password) {
        Player player = playerService.getPlayerByEmailAndPassword(emailIdentity.getEmail(), password);
        playerService.updateLastLogin(player.getId(), LocalDateTime.now());
        return player;
    }
}
