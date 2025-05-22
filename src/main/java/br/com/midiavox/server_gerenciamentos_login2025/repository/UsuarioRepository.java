package br.com.midiavox.server_gerenciamentos_login2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.midiavox.server_gerenciamentos_login2025.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}

