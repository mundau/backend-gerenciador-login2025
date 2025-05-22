package br.com.midiavox.server_gerenciamentos_login2025.configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.midiavox.server_gerenciamentos_login2025.model.Agente;
import br.com.midiavox.server_gerenciamentos_login2025.model.AgenteRamal;
import br.com.midiavox.server_gerenciamentos_login2025.model.Ramal;
import br.com.midiavox.server_gerenciamentos_login2025.repository.AgenteRamalRepository;
import br.com.midiavox.server_gerenciamentos_login2025.repository.AgenteRepository;
import br.com.midiavox.server_gerenciamentos_login2025.repository.RamalRepository;


@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AgenteRepository agenteRepo, RamalRepository ramalRepo, AgenteRamalRepository agenteRamalRepo) {
        return args -> {
            List<String> numerosRamal = List.of(
                "558889", "558870", "558865", "558860", "558855", "558850", "558845", "558840", "558835", "558830"
            );

            List<Ramal> ramais = new ArrayList<>();
            for (int i = 0; i < numerosRamal.size(); i++) {
                Ramal r = new Ramal();
                r.setNumero(numerosRamal.get(i));
                r.setStatus(i < 6 ? "ativo" : "inativo"); // primeros 6 activos
                ramalRepo.save(r);
                ramais.add(r);
            }

            List<String> nomes = List.of(
                "Carlos Ramírez", "Lucía González", "Pedro Alvarez", "Camila Duarte", "Javier Torres",
                "Valentina Ríos", "Mateo Castillo", "Isabela Herrera", "Santiago Vargas", "Florencia Méndez"
            );

            List<Agente> agentes = new ArrayList<>();
            for (int i = 0; i < nomes.size(); i++) {
                Agente a = new Agente();
                a.setNome(nomes.get(i));
                a.setEmail(nomes.get(i).toLowerCase().replace(" ", ".") + "@empresa.com");
                agenteRepo.save(a);
                agentes.add(a);
            }

            // Asignar 6 agentes activos
            for (int i = 0; i < 6; i++) {
                AgenteRamal ar = new AgenteRamal();
                ar.setAgente(agentes.get(i));
                ar.setRamal(ramais.get(i));
                ar.setInicio(LocalDateTime.now().minusDays(1));
                ar.setFim(null);
                agenteRamalRepo.save(ar);

                ramais.get(i).setAtual(ar);
                ramalRepo.save(ramais.get(i));
            }

            // Asignar 4 agentes inactivos (con fechas pasadas)
            for (int i = 6; i < 10; i++) {
                AgenteRamal ar = new AgenteRamal();
                ar.setAgente(agentes.get(i));
                ar.setRamal(ramais.get(i));
                ar.setInicio(LocalDateTime.now().minusDays(5));
                ar.setFim(LocalDateTime.now().minusDays(2));
                agenteRamalRepo.save(ar);
            }
        };
    }
}
