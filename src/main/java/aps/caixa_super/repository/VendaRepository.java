package aps.caixa_super.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import aps.caixa_super.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
