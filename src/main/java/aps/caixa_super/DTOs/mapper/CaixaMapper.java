package aps.caixa_super.DTOs.mapper;

import aps.caixa_super.DTOs.request.CaixaRequestDTO;
import aps.caixa_super.model.Caixa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CaixaMapper {
    public Caixa toEntity (CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = new Caixa();
        caixa.setNome(caixaRequestDTO.nome());
        return caixa;
    }
}
