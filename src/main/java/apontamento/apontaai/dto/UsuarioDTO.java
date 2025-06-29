package apontamento.apontaai.dto;

import apontamento.apontaai.domain.Usuario;

public record UsuarioDTO(Long id, String nome, String email) {
    public static UsuarioDTO of(Usuario usuario) {
        if (usuario == null) throw new RuntimeException("Usuario nulo no DTO");
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
