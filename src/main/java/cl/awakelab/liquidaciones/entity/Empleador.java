package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "empleador")
public class Empleador {
    @Id
    @Column(name = "id_empleador",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleador;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(length = 100,nullable = false)
    private  String nombre;

    @Column(name = "apellido_1",length = 100, nullable = false)
    private String apellido1;

    @Column(name = "apellido_2",length = 100)
    private String apellido2;

    @Column(length = 500)
    private String direccion;

    @Column(length = 100)
    private String email;

    //relacion con tabla usuario
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column
    private long telefono;

    //Relacion muchos a muchos de la tabla intermedia
    @ManyToMany
    @JoinTable(name = "empl_trab", //especifica la tabla intermedia que se utilizará para almacenar la relación.
            joinColumns = @JoinColumn(name = "id_empleador", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_trabajador", nullable = false))
    private List<Trabajador> trabajadores;
}
