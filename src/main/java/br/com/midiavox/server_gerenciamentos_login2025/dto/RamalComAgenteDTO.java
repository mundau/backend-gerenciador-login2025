package br.com.midiavox.server_gerenciamentos_login2025.dto;

import br.com.midiavox.server_gerenciamentos_login2025.model.AgenteRamal;
import br.com.midiavox.server_gerenciamentos_login2025.model.Ramal;
import br.com.midiavox.server_gerenciamentos_login2025.repository.AgenteRamalRepository;
import lombok.Data;

@Data
public class RamalComAgenteDTO {
    private Long id;
    private String numero;
    private String status;
    private String nomeAgente;

    public RamalComAgenteDTO(Ramal ramal, AgenteRamalRepository agenteRamalRepo) {
        this.id = ramal.getId();
        this.numero = ramal.getNumero();
        this.status = ramal.getStatus();

        AgenteRamal atualOuUltimo = ramal.getAtual();

        // Si no hay atual (porque fue cerrado), busca el último del histórico
        if (atualOuUltimo == null) {
            atualOuUltimo = agenteRamalRepo.findByRamalIdOrderByInicioDesc(ramal.getId())
                                           .stream()
                                           .findFirst()
                                           .orElse(null);
        }

        this.nomeAgente = atualOuUltimo != null ? atualOuUltimo.getAgente().getNome() : null;
    }

    // Getters
}
