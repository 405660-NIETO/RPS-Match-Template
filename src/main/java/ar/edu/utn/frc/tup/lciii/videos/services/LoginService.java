package ar.edu.utn.frc.tup.lciii.videos.services;

import ar.edu.utn.frc.tup.lciii.videos.dtos.login.Credential;
import ar.edu.utn.frc.tup.lciii.videos.dtos.login.CredentialV2;
import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Player login(Credential credential);
    Player login(CredentialV2 credential);
}
