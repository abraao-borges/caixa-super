package aps.caixa_super.repository;

import aps.caixa_super.entity.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @SuppressWarnings("null")
    Optional<Produto> findById(Long id);

    @Query("SELECT p FROM Produto p ORDER BY p.precoUnitario ASC")
    List<Produto> FindBySmallestToLargest();

    @Query("SELECT p FROM Produto p ORDER BY p.precoUnitario DESC")
    List<Produto> FindByLargestToSmallest();

    List<Produto> findByNome(@NotNull String nome);
}
