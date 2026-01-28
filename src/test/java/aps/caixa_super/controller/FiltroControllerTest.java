package aps.caixa_super.controller;

import aps.caixa_super.entity.Produto;
import aps.caixa_super.repository.GerenteRepository;
import aps.caixa_super.service.FiltroService;
import aps.caixa_super.service.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FiltroController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FiltroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FiltroService filtroService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private GerenteRepository gerenteRepository;

    @Test
    @DisplayName("Deve listar produtos do maior para o menor preço com status 200")
    void listarProdutosMaiorMenorSucesso() throws Exception {
        List<Produto> produtos = Collections.singletonList(new Produto());
        when(filtroService.ListarProdutoMaiorMenor()).thenReturn(ResponseEntity.ok(produtos));

        mockMvc.perform(get("/filtro/listar-produto-maior-menor"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve listar produtos do menor para o maior preço com status 200")
    void listarProdutosMenorMaiorSucesso() throws Exception {
        List<Produto> produtos = Collections.singletonList(new Produto());
        when(filtroService.ListarProdutoMenorMaior()).thenReturn(ResponseEntity.ok(produtos));

        mockMvc.perform(get("/filtro/listar-produto-menor-maior"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve listar produtos por nome usando RequestParam com status 200")
    void listarProdutoPorNomeSucesso() throws Exception {
        String nomeProduto = "Coca-Cola";
        List<Produto> produtos = Collections.singletonList(new Produto());
        when(filtroService.ListarProdutoPorNome(nomeProduto)).thenReturn(ResponseEntity.ok(produtos));

        mockMvc.perform(get("/filtro/listar-produto-por-nome")
                        .param("nome", nomeProduto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}