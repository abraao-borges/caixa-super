package aps.caixa_super.repository;

import aps.caixa_super.entity.BaixaDeCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaixaDeCaixaRepository extends JpaRepository <BaixaDeCaixa, Long> {
}
