package aps.caixa_super.DTOs.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record VendaRequestDTO(
        @NotNull(message = "O ID do caixa é obrigatório")
        Long caixaId,
        @NotEmpty(message = "A venda deve conter pelo menos um produto")
        @Valid
        List<ProdutoVendaDTO> produtos
) {}