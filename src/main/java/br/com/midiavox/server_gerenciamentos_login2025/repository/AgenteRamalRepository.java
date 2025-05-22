package br.com.midiavox.server_gerenciamentos_login2025.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midiavox.server_gerenciamentos_login2025.model.AgenteRamal;

public interface AgenteRamalRepository extends JpaRepository<AgenteRamal, Long> {
    List<AgenteRamal> findByRamalIdOrderByInicioDesc(Long ramalId);
    Optional<AgenteRamal> findByRamalIdAndFimIsNull(Long ramalId);
}
