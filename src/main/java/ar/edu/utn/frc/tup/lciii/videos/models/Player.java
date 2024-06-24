package ar.edu.utn.frc.tup.lciii.videos.models;

import ar.edu.utn.frc.tup.lciii.videos.utils.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Schema(title = "Player ID",  description = "The Player ID", example = "7")
    private Long id;

    @Schema(title = "Player Username",  description = "The Players username", example = "MyUsername")
    @NotNull(message = "Username can't be null")
    private String userName;

    @Schema(title = "Player Password",  description = "The Players password", example = "Password1337%")
    @NotNull(message = "Password can't be null")
    @ValidPassword
    private String password;

    @Schema(title = "Player e-mail",  description = "The Player email", example = "example@mail.com")
    @NotNull(message = "Email can't be null")
    @Email(message = "Needs to be a valid email")
    private String email;

    @Schema(title = "Player avatar",  description = "The Player avatar", example = "https://i.pinimg.com/564x/dd/17/a2/dd17a2b3aac1526d8ae0ba6df968ead4.jpg")
    private String avatar;

    //configuracion para todas las fechas
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Schema(title = "Player last login",  description = "Last time seen online", example = "02-02-2020 22:22:22", nullable = true)
    private LocalDateTime lastLogin;
}
