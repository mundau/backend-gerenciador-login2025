package br.com.midiavox.server_gerenciamentos_login2025.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.midiavox.server_gerenciamentos_login2025.dto.RamalComAgenteDTO;
import br.com.midiavox.server_gerenciamentos_login2025.model.Agente;
import br.com.midiavox.server_gerenciamentos_login2025.model.AgenteRamal;
import br.com.midiavox.server_gerenciamentos_login2025.model.Ramal;
import br.com.midiavox.server_gerenciamentos_login2025.repository.AgenteRamalRepository;
import br.com.midiavox.server_gerenciamentos_login2025.repository.AgenteRepository;
import br.com.midiavox.server_gerenciamentos_login2025.repository.RamalRepository;

@Service
public class AgenteRamalService {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private RamalRepository ramalRepository;

    @Autowired
    private AgenteRamalRepository agenteRamalRepository;

    public void asignarAgenteARamal(Long ramalId, Long agenteId) {
        Ramal ramal = ramalRepository.findById(ramalId)
                .orElseThrow(() -> new RuntimeException("Ramal no encontrado"));

        Agente agente = agenteRepository.findById(agenteId)
                .orElseThrow(() -> new RuntimeException("Agente no encontrado"));

        // Cerrar asignación actual si existe
        agenteRamalRepository.findByRamalIdAndFimIsNull(ramalId).ifPresent(asignacionActual -> {
            asignacionActual.setFim(LocalDateTime.now());
            agenteRamalRepository.save(asignacionActual);
        });

        // Crear nueva asignación
        AgenteRamal nuevaAsignacion = new AgenteRamal();
        nuevaAsignacion.setAgente(agente);
        nuevaAsignacion.setRamal(ramal);
        nuevaAsignacion.setInicio(LocalDateTime.now());
        nuevaAsignacion.setFim(null);

        agenteRamalRepository.save(nuevaAsignacion);

        // Actualizar referencia directa en Ramal
        ramal.setAtual(nuevaAsignacion);
        ramalRepository.save(ramal);
    }

    public Agente obtenerAgenteActual(Long ramalId) {
        return agenteRamalRepository.findByRamalIdAndFimIsNull(ramalId)
                .map(AgenteRamal::getAgente)
                .orElse(null);
    }

    public List<AgenteRamal> obtenerHistorico(Long ramalId) {
        return agenteRamalRepository.findByRamalIdOrderByInicioDesc(ramalId);
    }
    
    public List<RamalComAgenteDTO> listarRamaisConAgente() {
        List<Ramal> ramais = ramalRepository.findAll();
        return ramais.stream()
                .map(r -> new RamalComAgenteDTO(r, agenteRamalRepository))
                .collect(Collectors.toList());
    }


}