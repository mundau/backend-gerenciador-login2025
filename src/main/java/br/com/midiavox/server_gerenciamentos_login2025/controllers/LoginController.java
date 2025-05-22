package br.com.midiavox.server_gerenciamentos_login2025.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.midiavox.server_gerenciamentos_login2025.dto.UpdateUsuarioDTO;
import br.com.midiavox.server_gerenciamentos_login2025.model.Usuario;
import br.com.midiavox.server_gerenciamentos_login2025.service.LoginService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/auth")
    public ResponseEntity<String> autenticarUsuario(@RequestBody Usuario usuario) {
    	System.out.println("DEBUG LOGIN:");
    	System.out.println("Email recebido: " + usuario.getEmail());
    	System.out.println("Senha recebida: " + usuario.getPassword());

        System.out.println("valor de usuario:" + usuario);
        String resposta = loginService.autenticarUsuario(usuario);

        if (resposta.equals("Login realizado com sucesso.")) {
            return ResponseEntity.ok(resposta); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta); // 401 Unauthorized
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario usuario) {
        String resposta = loginService.cadastrarUsuario(usuario);
        
        if (resposta.equals("Usuário cadastrado com sucesso!")) {
        	 return ResponseEntity.status(HttpStatus.CREATED).body(resposta); // 201 Created
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta); // 401 Unauthorized
        }
        
    }

    @PutMapping("/update")
    public ResponseEntity<String> atualizarUsuario(@RequestBody UpdateUsuarioDTO updateUsuarioDTO) {
        String resposta = loginService.atualizarUsuario(updateUsuarioDTO);

        if (resposta.equals("Senha atualizada com sucesso!")) {
            return ResponseEntity.ok(resposta); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta); // 400 Bad Request
        }
    }
}
