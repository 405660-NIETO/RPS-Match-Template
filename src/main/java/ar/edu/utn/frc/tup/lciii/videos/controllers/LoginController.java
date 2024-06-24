package ar.edu.utn.frc.tup.lciii.videos.controllers;

import ar.edu.utn.frc.tup.lciii.videos.dtos.login.Credential;
import ar.edu.utn.frc.tup.lciii.videos.dtos.login.CredentialV2;
import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(
            summary = "Login a player in the platform",
            description = "Return the player logged in if the credentials are correct.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "User is not registered", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = Player.class))),
    })
    @PostMapping("")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid Credential credential) {
        return ResponseEntity.ok(loginService.login(credential));
    }

    @Operation(
            summary = "Login a player in the platform",
            description = "Return the player logged in if the credentials are correct.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "User or Email already used", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = Player.class))),
    })
    @PostMapping("/v2")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid CredentialV2 credentialV2) {
        return ResponseEntity.ok(loginService.login(credentialV2));
    }
}
