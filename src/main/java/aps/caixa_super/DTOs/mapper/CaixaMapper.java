package aps.caixa_super.DTOs.mapper;

import aps.caixa_super.DTOs.request.CaixaRequestDTO;
import aps.caixa_super.entity.Caixa;
import org.springframework.stereotype.Component;

@Component
public class CaixaMapper {
    public Caixa toEntity (CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = new Caixa();
        caixa.setNome(caixaRequestDTO.getNome());
        return caixa;
    }
}
