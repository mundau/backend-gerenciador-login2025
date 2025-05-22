package br.com.midiavox.server_gerenciamentos_login2025.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midiavox.server_gerenciamentos_login2025.model.Agente;

public interface AgenteRepository extends JpaRepository<Agente, Long> {
    Optional<Agente> findByEmail(String email);
}
