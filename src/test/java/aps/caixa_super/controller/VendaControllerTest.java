package aps.caixa_super.controller;

import aps.caixa_super.DTOs.request.ProdutoVendaDTO;
import aps.caixa_super.DTOs.request.VendaRequestDTO;
import aps.caixa_super.DTOs.response.CaixaResumoDTO;
import aps.caixa_super.DTOs.response.VendaResponseDTO;
import aps.caixa_super.entity.Venda;
import aps.caixa_super.repository.GerenteRepository;
import aps.caixa_super.service.TokenService;
import aps.caixa_super.service.VendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VendaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VendaService vendaService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private GerenteRepository gerenteRepository;

    @Test
    @DisplayName("Deve realizar uma venda e retornar 201 Created")
    void realizarVendaSucesso() throws Exception {
        // 1. Initialize Record with values (Records don't have empty constructors)
        VendaRequestDTO request = new VendaRequestDTO(1L, List.of(new ProdutoVendaDTO(1L, 2))); 
        
        Venda vendaMock = new Venda();
        vendaMock.setId(1L);

        when(vendaService.realizarVenda(any(VendaRequestDTO.class))).thenReturn(vendaMock);

        mockMvc.perform(post("/venda/realizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("Deve listar vendas e retornar 200 OK")
    void listarVendasSucesso() throws Exception {
        // 1. Setup the data (using Record constructors)
        VendaResponseDTO dto = new VendaResponseDTO(
            1L, 
            LocalDateTime.now(), 
            new BigDecimal("100.00"), 
            null // Or a new CaixaResumoDTO(...)
        );
        List<VendaResponseDTO> lista = Collections.singletonList(dto);

        // 2. Wrap the list in a ResponseEntity to satisfy the Service's return type
        Mockito.when(vendaService.listarVendas()).thenReturn(ResponseEntity.ok(lista));

        // 3. Perform the request
        mockMvc.perform(get("/venda/listar-vendas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @DisplayName("Deve deletar venda e retornar 204 No Content")
    void deletarVendaSucesso() throws Exception {
        Long id = 1L;
        when(vendaService.deletarVenda(id)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/venda/deletar" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar deletar venda inexistente")
    void deletarVendaErro() throws Exception {
        Long id = 99L;
        when(vendaService.deletarVenda(id)).thenThrow(new IllegalArgumentException("Venda não encontrada"));

        mockMvc.perform(delete("/venda/deletar" + id))
                .andExpect(status().isBadRequest());
    }
}