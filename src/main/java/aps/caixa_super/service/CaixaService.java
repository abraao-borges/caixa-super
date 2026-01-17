package aps.caixa_super.service;

import aps.caixa_super.DTOs.mapper.CaixaMapper;
import aps.caixa_super.DTOs.request.CaixaRequestDTO;
import aps.caixa_super.model.Caixa;
import aps.caixa_super.repository.CaixaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private CaixaMapper caixaMapper;

    public ResponseEntity<List<Caixa>> listarCaixas(){
        return ResponseEntity.ok().body(caixaRepository.findAll());
    }

    @Transactional
    public Caixa salvarCaixa(CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = caixaMapper.toEntity(caixaRequestDTO);
        return caixaRepository.save(caixa);
    }

    @Transactional
    public boolean deletarCaixa(Long id){
        if (caixaRepository.existsById(id)) {
            caixaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean caixaExiste(Long id) {
        return caixaRepository.existsById(id);
    }
}