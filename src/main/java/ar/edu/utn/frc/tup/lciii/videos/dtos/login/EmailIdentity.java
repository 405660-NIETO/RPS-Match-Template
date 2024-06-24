package ar.edu.utn.frc.tup.lciii.videos.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailIdentity extends Identity {
    @Schema(title = "Email to log in",
    description = "The player's email",
    example = "example@mail.com",
    nullable = false)
    @NotNull(message = "email can't be null")
    @JsonProperty("email")
    private String email;
}
