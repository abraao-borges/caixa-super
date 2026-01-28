package aps.caixa_super.controller;

import aps.caixa_super.DTOs.request.CaixaRequestDTO;
import aps.caixa_super.entity.Caixa;
import aps.caixa_super.service.CaixaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // 'addFilters = false' ignores Security for these tests
class CaixaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CaixaService caixaService;

    @Test
    @DisplayName("Deve listar caixas e retornar 200 OK")
    void listarCaixas_Sucesso() throws Exception {
        Caixa caixa = new Caixa();
        caixa.setId(1L);
        caixa.setNome("Caixa Central");
        
        List<Caixa> lista = List.of(caixa);

        // Since your Controller returns exactly what the service returns:
        Mockito.when(caixaService.listarCaixas()).thenReturn(ResponseEntity.ok(lista));

        mockMvc.perform(get("/caixa/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Caixa Central"));
    }

    @Test
    @DisplayName("Deve criar um caixa e retornar 201 Created")
    void criarCaixa_Sucesso() throws Exception {
        CaixaRequestDTO request = new CaixaRequestDTO("Novo Caixa");
        
        Caixa caixaSalva = new Caixa();
        caixaSalva.setId(10L);
        caixaSalva.setNome("Novo Caixa");

        Mockito.when(caixaService.salvarCaixa(any(CaixaRequestDTO.class))).thenReturn(caixaSalva);

        mockMvc.perform(post("/caixa/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Novo Caixa"));
    }

    @Test
    @DisplayName("Deve deletar um caixa e retornar 204 No Content")
    void deletarCaixa_Sucesso() throws Exception {
        Mockito.when(caixaService.deletarCaixa(1L)).thenReturn(true);

        mockMvc.perform(delete("/caixa/Deletar/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar deletar caixa inexistente")
    void deletarCaixa_NaoEncontrado() throws Exception {
        Mockito.when(caixaService.deletarCaixa(99L)).thenReturn(false);

        mockMvc.perform(delete("/caixa/Deletar/99"))
                .andExpect(status().isNotFound());
    }
}