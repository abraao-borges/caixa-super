package aps.caixa_super.controller;

import aps.caixa_super.entity.Produto;
import aps.caixa_super.service.FiltroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filtro")
public class FiltroController {

    @Autowired
    FiltroService filtroService;

    @GetMapping("/listar-produto-maior-menor")
    public ResponseEntity<List<Produto>> listarProdutosMaiorMenor() {
        return filtroService.ListarProdutoMaiorMenor();
    }

    @GetMapping("/listar-produto-menor-maior")
    public ResponseEntity<List<Produto>> listarProdutosMenorMaior() {
        return filtroService.ListarProdutoMenorMaior();
    }

    @GetMapping("/listar-produto-por-nome")
    public ResponseEntity<List<Produto>> listarProdutoPorNome(@RequestParam String nome) {
        return filtroService.ListarProdutoPorNome(nome);
    }
}