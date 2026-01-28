package aps.caixa_super.DTOs.mapper;

import aps.caixa_super.DTOs.request.ProdutoRequestDTO;
import aps.caixa_super.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity (ProdutoRequestDTO produtoRequestDTO){
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.nome());
        produto.setQuantidade(produtoRequestDTO.quantidade());
        produto.setPrecoUnitario(produtoRequestDTO.precoUnitario());
        produto.setTipo(produtoRequestDTO.tipo());

        return produto;
    }
}
