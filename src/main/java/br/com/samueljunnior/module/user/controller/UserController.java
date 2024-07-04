package br.com.samueljunnior.module.user.controller;

import br.com.samueljunnior.module.user.dto.UserCreateDTO;
import br.com.samueljunnior.module.user.dto.UserDTO;
import br.com.samueljunnior.module.user.dto.UserFilterDTO;
import br.com.samueljunnior.module.user.enums.UserTypeEnum;
import br.com.samueljunnior.module.user.service.UserService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@Tag(
        name = "User",
        description = "Operações de usuário.",
        externalDocs = @ExternalDocumentation(description = "Developer Website", url = "https://samueljunnior.github.io/about-me/")
)
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Cadatra um novo usuário.",
            description = "Realiza o cadastro de novo usuário a partir dos dados informados.",
            responses = @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            schema = @Schema(
                                    implementation = UserDTO.class
                            )
                    )
            )
    )
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateDTO user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }

    @GetMapping
    @Operation(
            summary = "Buscar todos os usuarios.",
            description = "Retorna os usuários pelo filtro. Caso não seja informado nenhum filtro, retorna todos os usuários.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = List.class
                            )
                    )
            )
    )
    @Parameters({
            @Parameter(name = "document", description = "CPF ou CNPJ sem pontuação.", in = ParameterIn.QUERY, schema = @Schema(type = "string", implementation = String.class)),
            @Parameter(name = "email", description = "E-mail do usuário", in = ParameterIn.QUERY, schema = @Schema(type = "string", implementation = String.class)),
            @Parameter(name = "type", description = "Tipo de usuario.", in = ParameterIn.QUERY, schema = @Schema(type = "string", implementation = UserTypeEnum.class)),
            @Parameter(name = "filter", hidden = true)
    })
    public ResponseEntity<List<UserDTO>> findUsersByFilter(UserFilterDTO filter){
        return ResponseEntity.ok(userService.findUsersByFilter(filter));
    }
}
