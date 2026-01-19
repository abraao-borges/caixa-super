package aps.caixa_super.service;

import aps.caixa_super.DTOs.mapper.CaixaMapper;
import aps.caixa_super.DTOs.request.CaixaRequestDTO;
import aps.caixa_super.entity.Caixa;
import aps.caixa_super.repository.CaixaRepository;
import aps.caixa_super.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    @Autowired
    CaixaRepository caixaRepository;

    @Autowired
    CaixaMapper caixaMapper;

    @Autowired
    ProdutoRepository produtoRepository;

    public ResponseEntity<List<Caixa>> listarCaixas(){
        return ResponseEntity.ok().body(caixaRepository.findAll());
    }

    @Transactional
    public Caixa salvarCaixa(CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = caixaMapper.toEntity(caixaRequestDTO);
        return caixaRepository.save(caixa);
    }

    public void deletarCaixa(Long id){
        caixaRepository.deleteById(id);
    }
    public boolean caixaExiste(Long id) {
        return produtoRepository.existsById(id);
    }
}
