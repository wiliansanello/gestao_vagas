package br.com.wiliansanello.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedora React")
    private String description;

    @Schema(example = "tati_pinheiro")
    private String username;

    @Schema(example = "tatipinheiro@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "Tatiana Pinheiro")
    private String name;
    
}
