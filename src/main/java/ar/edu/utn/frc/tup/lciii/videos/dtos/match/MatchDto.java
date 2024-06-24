package ar.edu.utn.frc.tup.lciii.videos.dtos.match;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {

    @NotNull(message = "Game id can't be null!")
    private Long gameId;
    @NotNull(message = "Player id can't be null!")
    private Long playerId;
}
