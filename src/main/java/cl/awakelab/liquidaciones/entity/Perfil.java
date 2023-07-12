package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table( name = "perfil")
public class Perfil {
    @Id
    @Column(nullable = false)
    private int id_perfil;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "perfil")// nombre del atributo tipo perfil de la clase usuario
    private List<Usuario> usuarios;

}
