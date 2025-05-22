package br.com.midiavox.server_gerenciamentos_login2025.model;

import java.util.List;

import jakarta.persistence.Id;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL)
    private List<AgenteRamal> historicoRamais;

}