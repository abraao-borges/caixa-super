package aps.caixa_super.DTOs.request;

import aps.caixa_super.entity.Tipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDTO {

    String nome;

    double quantidade;

    BigDecimal precoUnitario;

    Tipo tipo;
}
