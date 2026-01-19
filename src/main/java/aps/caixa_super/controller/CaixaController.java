package aps.caixa_super.controller;

import aps.caixa_super.DTOs.request.CaixaRequestDTO;
import aps.caixa_super.entity.Caixa;
import aps.caixa_super.service.CaixaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixa")
@SecurityRequirement(name = "bearer-key")
public class CaixaController {

    @Autowired
    CaixaService caixaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Caixa>> listarCaixas(){
        return caixaService.listarCaixas();
    }

    @PostMapping("/criar")
    public ResponseEntity <Caixa> criarCaixa(@RequestBody CaixaRequestDTO caixa) {
        Caixa criaCaixa = caixaService.salvarCaixa(caixa);
        return ResponseEntity.status(HttpStatus.CREATED).body(criaCaixa);
    }

    @DeleteMapping("/Deletar/{id}")
    public ResponseEntity<Void> deletarCaixa(@PathVariable Long id) {
        if (!caixaService.caixaExiste(id)) {
            return ResponseEntity.notFound().build();  // Retorna 404 se não encontrar o caixa
        }
        caixaService.deletarCaixa(id);
        return ResponseEntity.noContent().build();
    }
}
