package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(unique = true, nullable = false)
    private int run;

    @Column(length = 200, nullable = false)
    private String clave;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 200, nullable = false)
    private String apellido_1;

    @Column(length = 100)
    private String apellido_2;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perfil", nullable = false)
    private Perfil perfil;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime fecha_creacion;

    @Column
    private long telefono;

    @OneToMany(mappedBy = "usuario") //hace referencia al atributo tipo Usuario de la clase empleador
    private List<Empleador> empleadores;

}
