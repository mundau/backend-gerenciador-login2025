package br.com.midiavox.server_gerenciamentos_login2025.service;


import org.springframework.stereotype.Service;

import br.com.midiavox.server_gerenciamentos_login2025.dto.UpdateUsuarioDTO;
import br.com.midiavox.server_gerenciamentos_login2025.model.Usuario;
import br.com.midiavox.server_gerenciamentos_login2025.repository.UsuarioRepository;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;


    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String autenticarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        
        if (usuarioExistente != null && usuarioExistente.getPassword().equals(usuario.getPassword())) {
            return "Login realizado com sucesso.";
        } else {
            return "Email ou senha incorretos.";
        }
    }

    public String cadastrarUsuario(Usuario usuario) {
    	
    	Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
    	if(usuarioExistente != null) {
    		return "usuario ya existe con ese email";
    	}
        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    public String atualizarUsuario(UpdateUsuarioDTO updateUsuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(updateUsuarioDTO.getEmail());

        if (usuarioExistente != null && usuarioExistente.getPassword().equals(updateUsuarioDTO.getPassword())) {
            usuarioExistente.setPassword(updateUsuarioDTO.getNewPassword());
            usuarioRepository.save(usuarioExistente);
            return "Senha atualizada com sucesso!";
        } else {
            return "Usuário não encontrado ou senha atual incorreta.";
        }
    }
}
