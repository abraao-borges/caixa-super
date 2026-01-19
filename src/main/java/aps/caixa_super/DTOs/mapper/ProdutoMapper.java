package aps.caixa_super.DTOs.mapper;

import aps.caixa_super.DTOs.request.ProdutoRequestDTO;
import aps.caixa_super.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity (ProdutoRequestDTO produtoRequestDTO){
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.getNome());
        produto.setQuantidade(produtoRequestDTO.getQuantidade());
        produto.setPrecoUnitario(produtoRequestDTO.getPrecoUnitario());
        produto.setTipo(produtoRequestDTO.getTipo());

        return produto;
    }
}
