package aps.caixa_super.DTOs.mapper;

import aps.caixa_super.DTOs.response.CaixaResumoDTO;
import aps.caixa_super.DTOs.response.VendaResponseDTO;
import aps.caixa_super.entity.Venda;

import java.util.List;
import java.util.stream.Collectors;

public class VendaMapper {
    public static VendaResponseDTO toDTO(Venda venda) {
        CaixaResumoDTO caixaDTO = new CaixaResumoDTO(
                venda.getCaixa().getId(),
                venda.getCaixa().getNome()
        );

        return new VendaResponseDTO(
                venda.getId(),
                venda.getDataDeVenda(),
                venda.getValorTotal(),
                caixaDTO
        );
    }

    public static List<VendaResponseDTO> toDTOList(List<Venda> vendas) {
        return vendas.stream().map(VendaMapper::toDTO).collect(Collectors.toList());
    }
}