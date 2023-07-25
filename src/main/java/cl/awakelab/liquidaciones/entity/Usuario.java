package cl.awakelab.liquidaciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name="id_usuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //se utiliza para indicar cómo se generará el valor de la clave primaria. se utiliza la estrategia de generación de identidad. lo que significa que la generación del valor se delega al motor de la base de datos.
    private int idUsuario;

    @Column(unique = true, nullable = false)
    private int run;

    @Column(length = 200, nullable = false)
    private String clave;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido_1",length = 200, nullable = false)
    private String apellido1;

    @Column(name = "apellido_2",length = 100)
    private String apellido2;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perfil", nullable = false) //se utiliza para especificar la columna de la tabla de la base de datos que se utilizará para establecer la relación.
    private Perfil perfil;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "fecha_creacion",nullable = false)
    private LocalDateTime fechaCreacion;

    @Column
    private long telefono;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario") //hace referencia al atributo tipo Usuario de la clase empleador
    private List<Empleador> empleadores;

}
