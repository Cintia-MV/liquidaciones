package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity //puede ser mapeada a una tabla en una base de datos
@Table( name = "perfil") //especifica el nombre de la tabla en la base de datos a la que se va a mapear la entidad Perfil.
public class Perfil {
    @Id //indica que es la clave primaria de la entidad Perfil
    @Column(nullable = false)
    private int id_perfil;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean estado;

    //indica que un perfil puede tener varios usuarios
    @OneToMany(mappedBy = "perfil")// la relación es mapeada por el atributo perfil
    private List<Usuario> usuarios;

}
