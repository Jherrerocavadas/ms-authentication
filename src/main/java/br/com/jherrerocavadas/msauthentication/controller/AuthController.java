package br.com.jherrerocavadas.msauthentication.controller;

import br.com.jherrerocavadas.msauthentication.dto.request.TokenGenerationRequestDTO;
import br.com.jherrerocavadas.msauthentication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth/v1")
@Tag(name = "Autenticação", description = "Operação de autenticação das APIs")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Operation(summary =  "Gerar token de autenticação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "Token gerado"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/generate-token")
    public ResponseEntity<Object> autenticar(@RequestBody TokenGenerationRequestDTO tokenGenerationRequestDTO){
        return ResponseEntity.ok(authenticationService.autenticar(tokenGenerationRequestDTO));
    }

    @Operation(summary =  "Checar token de autenticação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Token verificado"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/check-token")
    public ResponseEntity<Object> checarAutenticacao(@RequestHeader("X-API-TOKEN") String token){
        return ResponseEntity.ok(authenticationService.checarAutenticacao(token));
    }
}
