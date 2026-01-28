package aps.caixa_super.service;

import aps.caixa_super.DTOs.mapper.ProdutoMapper;
import aps.caixa_super.DTOs.request.ProdutoRequestDTO;
import aps.caixa_super.entity.Produto;
import aps.caixa_super.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public Produto salvaProduto(ProdutoRequestDTO produtoRequestDTO){
        Produto produto = produtoMapper.toEntity(produtoRequestDTO);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Optional<Produto> atualizarPreco(Long id, BigDecimal novoPreco) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setPrecoUnitario(novoPreco);
                    return produtoRepository.save(produto);
                });
    }

    @Transactional
    public boolean deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean produtoExiste(Long id) {
        return produtoRepository.existsById(id);
    }
}