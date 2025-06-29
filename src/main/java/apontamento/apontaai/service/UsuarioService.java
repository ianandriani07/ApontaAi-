package apontamento.apontaai.service;

import apontamento.apontaai.domain.Usuario;
import apontamento.apontaai.dto.CadastroUsuarioDTO;
import apontamento.apontaai.dto.LoginDTO;
import apontamento.apontaai.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final Argon2PasswordEncoder encoder;

    public Usuario cadastrar(CadastroUsuarioDTO novoUsuario) {
        System.out.println("Recebendo DTO: " + novoUsuario);
        Usuario usuario = Usuario.builder()
                .nome(novoUsuario.nome())
                .email(novoUsuario.email())
                .senha(encoder.encode(novoUsuario.senha()))
                .build();
        Usuario salvo = repository.save(usuario);
        System.out.println("Usuário salvo: " + salvo);
        return salvo;
    }

    public Usuario autenticar(LoginDTO login) {
        var user = repository.findByEmail(login.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        if(!encoder.matches(login.senha(), user.getSenha()))
            throw new RuntimeException("Credências inválidas");
        return user;
    }

}
