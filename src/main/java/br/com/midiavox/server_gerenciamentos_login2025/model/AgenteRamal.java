package br.com.midiavox.server_gerenciamentos_login2025.model;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class AgenteRamal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @ManyToOne
    @JoinColumn(name = "ramal_id")
    private Ramal ramal;

    private LocalDateTime inicio;
    private LocalDateTime fim;
}
