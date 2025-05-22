package br.com.midiavox.server_gerenciamentos_login2025.model;

import java.util.List;

import jakarta.persistence.Id;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Ramal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String status;

    // Histórico completo de asignaciones
    @OneToMany(mappedBy = "ramal", cascade = CascadeType.ALL)
    private List<AgenteRamal> historicoAgentes;

    // Agente actual (opcional para consulta rápida)
    @OneToOne
    @JoinColumn(name = "agente_ramal_ativo_id")
    private AgenteRamal atual;

    // Getters y setters
}
