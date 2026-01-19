package aps.caixa_super.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Positive(message = "O valor deve ser positivo")
    private double quantidade;

    @NotNull
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal precoUnitario;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}