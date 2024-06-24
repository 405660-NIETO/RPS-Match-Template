package ar.edu.utn.frc.tup.lciii.videos.controllers;

import ar.edu.utn.frc.tup.lciii.videos.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.videos.models.Match;
import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.services.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Operation(
            summary = "Create a new Player",
            description = "Returns the player created with an id. If the another player exists with the same username or email " +
                    "then will return a 400 status code. Additionally, the email must be valid and the password must " +
                    "have at least 8 characters and contain at least one number, " +
                    "one lowercase letter, one uppercase letter, and one special character."
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Username or Email already exists", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "200", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = Player.class))),
    })
    @PostMapping("")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid MatchDto matchDto) {
        Match matchSaved = matchService.createMatch(matchDto);

        if (Objects.isNull(matchSaved)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has an error");
        }
        else {
            return ResponseEntity.ok(matchSaved);
        }
    }

    @GetMapping("player/{id}/")
    public ResponseEntity<List<Match>> getMatchesOfPlayer(@PathVariable Long id) {
        List<Match> matches = matchService.getMatchesByPlayer(id);
        return ResponseEntity.ok(matches);
    }
}
