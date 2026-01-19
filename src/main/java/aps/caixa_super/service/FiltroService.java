package aps.caixa_super.service;

import aps.caixa_super.entity.Produto;
import aps.caixa_super.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltroService {

    @Autowired
    ProdutoRepository produtoRepository;


    public ResponseEntity<List<Produto>> ListarProdutoMenorMaior(){
        return ResponseEntity.ok().body(produtoRepository.FindBySmallestToLargest());
    }

    public ResponseEntity<List<Produto>> ListarProdutoMaiorMenor(){
        return ResponseEntity.ok().body(produtoRepository.FindByLargestToSmallest());
    }

    public ResponseEntity<List<Produto>> ListarProdutoPorNome(String nome){
        return ResponseEntity.ok().body(produtoRepository.findByNome(nome));
    }
}
