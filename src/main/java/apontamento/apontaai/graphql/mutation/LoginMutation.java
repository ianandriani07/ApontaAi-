package apontamento.apontaai.graphql.mutation;

import apontamento.apontaai.domain.Usuario;
import apontamento.apontaai.dto.AuthPayload;
import apontamento.apontaai.dto.LoginDTO;
import apontamento.apontaai.dto.UsuarioDTO;
import apontamento.apontaai.security.JwtUtil;
import apontamento.apontaai.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LoginMutation {
    private final UsuarioService service;
    private final JwtUtil jwt;

    @MutationMapping
    public AuthPayload login(@Argument LoginDTO input) {
        Usuario usuario = service.autenticar(input);
        String token = jwt.generateToken(usuario.getEmail(), usuario.getId());
        return new AuthPayload(token, UsuarioDTO.of(usuario));
    }
}
