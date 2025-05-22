package br.com.midiavox.server_gerenciamentos_login2025.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.midiavox.server_gerenciamentos_login2025.dto.RamalComAgenteDTO;
import br.com.midiavox.server_gerenciamentos_login2025.model.Agente;
import br.com.midiavox.server_gerenciamentos_login2025.model.AgenteRamal;
import br.com.midiavox.server_gerenciamentos_login2025.service.AgenteRamalService;

@RestController
@RequestMapping("/agente-ramal")
public class AgenteRamalController {

    @Autowired
    private AgenteRamalService agenteRamalService;

    @PostMapping("/asignar")
    public ResponseEntity<String> asignar(@RequestParam Long ramalId, @RequestParam Long agenteId) {
        agenteRamalService.asignarAgenteARamal(ramalId, agenteId);
        return ResponseEntity.ok("Agente asignado al ramal exitosamente.");
    }

    @GetMapping("/actual/{ramalId}")
    public ResponseEntity<Agente> getAgenteActual(@PathVariable Long ramalId) {
        Agente agente = agenteRamalService.obtenerAgenteActual(ramalId);
        return ResponseEntity.ok(agente);
    }

    @GetMapping("/historico/{ramalId}")
    public ResponseEntity<List<AgenteRamal>> getHistorico(@PathVariable Long ramalId) {
        List<AgenteRamal> historico = agenteRamalService.obtenerHistorico(ramalId);
        return ResponseEntity.ok(historico);
    }
    
    @GetMapping("/ramais")
    public ResponseEntity<List<RamalComAgenteDTO>> listarRamaisConAgente() {
        return ResponseEntity.ok(agenteRamalService.listarRamaisConAgente());
    }

}
