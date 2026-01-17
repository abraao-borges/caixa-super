package aps.caixa_super.DTOs.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaResponseDTO(
        Long id,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataDeVenda,

        BigDecimal valorTotal,

        CaixaResumoDTO caixa
) {}

