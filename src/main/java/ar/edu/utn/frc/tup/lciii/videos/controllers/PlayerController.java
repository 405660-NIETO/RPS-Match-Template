package ar.edu.utn.frc.tup.lciii.videos.controllers;

import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.services.PlayerService;
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

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(
            summary = "Get a player by id",
            description = "Returns a player by their id. If the player doesn't exist will then throw a 404 status code"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
                @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content =
                @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "200", description = "Internal Server Error", content =
                @Content(schema = @Schema(implementation = Player.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id){
        //return ResponseEntity.ok(playerService.getPlayerById(id));
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.ok(player);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Player> getTest(@PathVariable Long id){
        Player player = new Player();
        player.setId(id);
        player.setUserName("Agus");
        player.setAvatar("pfp0377.jpg");
        player.setEmail("test@mail.com");
        player.setLastLogin(LocalDateTime.now());
        player.setPassword("admin");
        return ResponseEntity.ok(player);
    }

    /*
    Se creo el controlador que va a permitir que se le pase un player y este
    sea guardado en la base de datos

    La anotacion valid hace que entren en efecto las anotaciones de validacion en la clase Player
     */
    @Operation(
            summary = "Create a new player",
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
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player) {
        Player playerSaved = playerService.savePlayer(player);
        // usamos la clase Object para saber si el objeto es Nulo
        // tambien se podria con "playerSaver == null"
        if (Objects.isNull(playerSaved)) {
            // se crea la excepcion para informar que el usuario ya existe mediante un Bad Request
            // no queremos que se
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Email already exists!");
        }
        else {
            return ResponseEntity.ok(playerSaved);
        }
    }
}
