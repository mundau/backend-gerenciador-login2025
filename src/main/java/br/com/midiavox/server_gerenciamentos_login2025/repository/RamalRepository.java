package br.com.midiavox.server_gerenciamentos_login2025.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midiavox.server_gerenciamentos_login2025.model.Ramal;

public interface RamalRepository extends JpaRepository<Ramal, Long> {
    Optional<Ramal> findByNumero(String numero);
}
