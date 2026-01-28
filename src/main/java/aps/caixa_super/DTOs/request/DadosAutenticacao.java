package aps.caixa_super.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank String login,
        @NotBlank String senha
) {}