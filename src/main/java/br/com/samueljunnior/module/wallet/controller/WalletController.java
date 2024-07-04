package br.com.samueljunnior.module.wallet.controller;

import br.com.samueljunnior.module.wallet.dto.DepositValueDTO;
import br.com.samueljunnior.module.wallet.dto.WalletDTO;
import br.com.samueljunnior.module.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/wallet")
@Tag(
        name = "Wallet",
        description = "Operações sobre carteira.",
        externalDocs = @ExternalDocumentation(description = "Developer Website", url = "https://samueljunnior.github.io/about-me/")
)
public class WalletController {

    private final WalletService walletService;

    @Operation(
            summary = "Buscar carteira do usuário.",
            description = "Retorna as informações da carteira de um usuário.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = WalletDTO.class
                            )
                    )
            )
    )
    @GetMapping("/user/{idUser}")
    public ResponseEntity<WalletDTO> getWalletByUser(@PathVariable(name = "idUser") Long idUser){
        return ResponseEntity.ok(walletService.getWalletDataByUser(idUser));
    }

    @Operation(
            summary = "Depositar valor.",
            description = "Realiza um dpeosito na carteido do usuariosinformado.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = WalletDTO.class
                            )
                    )
            )
    )
    @PostMapping("/deposit")
    public ResponseEntity<WalletDTO> makeDeposit(@RequestBody @Valid DepositValueDTO dto){
        return ResponseEntity.ok(walletService.makeDeposit(dto));
    }}
