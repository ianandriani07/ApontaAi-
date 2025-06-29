package apontamento.apontaai.graphql.query;

import apontamento.apontaai.domain.Usuario;
import apontamento.apontaai.dto.UsuarioDTO;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UsuarioQuery {

    @QueryMapping
    public UsuarioDTO me(Authentication auth) {
        Usuario user = (Usuario) auth.getPrincipal();
        return UsuarioDTO.of(user);
    }
}
