package apontamento.apontaai.graphql.mutation;

import apontamento.apontaai.dto.CadastroUsuarioDTO;
import apontamento.apontaai.dto.UsuarioDTO;
import apontamento.apontaai.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CadastroMutation {
    private final UsuarioService service;

    @MutationMapping("cadastrarUsuario")
    public UsuarioDTO cadastrarUsuario(@Argument("cadastro") CadastroUsuarioDTO dto) {
        try {
            return UsuarioDTO.of(service.cadastrar(dto));
        } catch (Exception e) {
            throw new RuntimeException("Falha ao cadastrar usuário: " + e.getMessage());
        }
    }

}
