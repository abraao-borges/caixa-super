package aps.caixa_super.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record CaixaRequestDTO(
        @NotBlank(message = "O nome do caixa é obrigatório")
        String nome
) {}
