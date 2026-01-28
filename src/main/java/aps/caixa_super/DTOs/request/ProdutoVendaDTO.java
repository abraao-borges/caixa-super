package aps.caixa_super.DTOs.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoVendaDTO(
        @NotNull(message = "O ID do produto é obrigatório")
        Long produtoId,

        @Positive(message = "A quantidade deve ser maior que zero")
        double quantidade
) {}
