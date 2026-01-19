package aps.caixa_super.controller;

import java.math.BigDecimal;
import java.util.List;

import aps.caixa_super.DTOs.request.ProdutoRequestDTO;
import aps.caixa_super.repository.ProdutoRepository;
import aps.caixa_super.service.ProdutoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import aps.caixa_super.entity.Produto;

@RestController
@RequestMapping("/produto")
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {
    @Autowired
    ProdutoService ProdutoService;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/listar-produto")
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ProdutoService.listarProdutos();
    }

    @GetMapping("/detalhar-produto/{id}")
    public ResponseEntity<Produto> detalharProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok().body(produto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/criar")
    public ResponseEntity <Produto> criarProduto(@Valid @RequestBody ProdutoRequestDTO produto) {
        Produto criaPoduto = ProdutoService.salvaProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criaPoduto);
    }

    @PutMapping("/atualizar-preco")
    public Produto atualizarProduto(@RequestParam Long id, @RequestParam BigDecimal novoPreco) {
        return ProdutoService.atualizarPreco(id, novoPreco);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@RequestParam Long id) {
        if (!ProdutoService.produtoExiste(id)) {
            return ResponseEntity.notFound().build();
        }
        ProdutoService.deletarProduto(id);
        return ResponseEntity.noContent().build(); //204
    }
}