package br.com.samueljunnior.module.transfer.controller;

import br.com.samueljunnior.module.transfer.dto.CreateTransferDTO;
import br.com.samueljunnior.module.transfer.dto.TransferDTO;
import br.com.samueljunnior.module.transfer.service.TransferService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
@Tag(
        name = "Tranfer",
        description = "Operações de tranferências.",
        externalDocs = @ExternalDocumentation(description = "Developer Website", url = "https://samueljunnior.github.io/about-me/")
)
public class TransferController {

    private final TransferService transferService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Cria uma transferência.",
            description = "Realiza transferência simplificada entre usuários.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = TransferDTO.class
                            )
                    )
            )
    )
    public ResponseEntity<TransferDTO> transfer(@RequestBody @Valid CreateTransferDTO dto){
        return ResponseEntity.ok(transferService.transfer(dto));
    }

}
