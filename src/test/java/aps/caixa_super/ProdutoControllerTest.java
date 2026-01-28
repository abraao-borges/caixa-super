package aps.caixa_super;

import aps.caixa_super.repository.ProdutoRepository;
import aps.caixa_super.controller.ProdutoController;
import aps.caixa_super.entity.Produto;
import aps.caixa_super.repository.GerenteRepository; // Adicione este import
import aps.caixa_super.service.ProdutoService;
import aps.caixa_super.service.TokenService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(ProdutoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private GerenteRepository gerenteRepository; // NECESSÁRIO para o SecurityFilter carregar [cite: 3]

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProdutos_DeveRetornar200() throws Exception {
        Mockito.when(produtoService.listarProdutos()).thenReturn(ResponseEntity.ok(List.of()));
        mockMvc.perform(get("/produto/listar-produto")).andExpect(status().isOk());
    }

    @Test
    void detalharProduto_DeveRetornar200_QuandoExiste() throws Exception {
        Produto p = new Produto();
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(p));
        mockMvc.perform(get("/produto/detalhar-produto/1")).andExpect(status().isOk());
    }

    @Test
    void detalharProduto_DeveRetornar404_QuandoNaoExiste() throws Exception {
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/produto/detalhar-produto/1")).andExpect(status().isNotFound());
    }

    @Test
    void deletarProduto_DeveRetornar204_QuandoExiste() throws Exception {
        Mockito.when(produtoService.produtoExiste(1L)).thenReturn(true);
        // Ajustado para usar .param() pois seu controller usa @RequestParam
        mockMvc.perform(delete("/produto/deletar/1").param("id", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletarProduto_DeveRetornar404_QuandoNaoExiste() throws Exception {
        Mockito.when(produtoService.produtoExiste(1L)).thenReturn(false);
        mockMvc.perform(delete("/produto/deletar/1").param("id", "1"))
                .andExpect(status().isNotFound());
    }
}