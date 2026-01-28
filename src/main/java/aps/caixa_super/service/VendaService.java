package aps.caixa_super.service;

import aps.caixa_super.DTOs.mapper.CaixaMapper;
import aps.caixa_super.DTOs.mapper.VendaMapper;
import aps.caixa_super.DTOs.request.ProdutoVendaDTO;
import aps.caixa_super.DTOs.request.VendaRequestDTO;
import aps.caixa_super.DTOs.response.VendaResponseDTO;
import aps.caixa_super.entity.*;
import aps.caixa_super.repository.ProdutoRepository;
import aps.caixa_super.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import aps.caixa_super.repository.CaixaRepository;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private CaixaMapper caixaMapper;

    @Transactional
    public Venda realizarVenda(VendaRequestDTO vendaRequest) {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ProdutoVendaDTO produtoVendaDTO : vendaRequest.produtos()) {
            Produto produto = produtoRepository.findById(produtoVendaDTO.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + produtoVendaDTO.produtoId()));

            if (produto.getQuantidade() < produtoVendaDTO.quantidade()) {
                    throw new IllegalArgumentException("Quantidade insuficiente do produto: " + produto.getNome());
            }

            BigDecimal valorProduto = produto.getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(produtoVendaDTO.quantidade()));
            valorTotal = valorTotal.add(valorProduto);

            produto.setQuantidade(produto.getQuantidade() - produtoVendaDTO.quantidade());
            produtoRepository.save(produto);
        }

        Caixa caixa = caixaRepository.findById(vendaRequest.caixaId())
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado com ID: " + vendaRequest.caixaId()));

        caixa.setQuantiaArmazenada(caixa.getQuantiaArmazenada().add(valorTotal));
        caixaRepository.save(caixa);

        Venda venda = new Venda();
        venda.setDataDeVenda(LocalDateTime.now());
        venda.setValorTotal(valorTotal);
        venda.setCaixa(caixa);

        return vendaRepository.save(venda);
    }

    public ResponseEntity<List<VendaResponseDTO>> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        List<VendaResponseDTO> vendasDTO = VendaMapper.toDTOList(vendas);
        return ResponseEntity.ok(vendasDTO);
    }

    @Transactional
    public ResponseEntity<Void> deletarVenda(Long vendaId) {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com ID: " + vendaId));

        vendaRepository.delete(venda);
        return ResponseEntity.noContent().build();
    }

}
