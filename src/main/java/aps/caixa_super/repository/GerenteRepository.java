package aps.caixa_super.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aps.caixa_super.entity.Gerente;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    UserDetails findByLogin(String login);
}
