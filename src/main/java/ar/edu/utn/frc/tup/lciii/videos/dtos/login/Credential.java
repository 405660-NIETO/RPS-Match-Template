package ar.edu.utn.frc.tup.lciii.videos.dtos.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    private Identity identity;
    private String password;
}
