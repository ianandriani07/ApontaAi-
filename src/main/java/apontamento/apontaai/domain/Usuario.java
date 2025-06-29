package apontamento.apontaai.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

}
