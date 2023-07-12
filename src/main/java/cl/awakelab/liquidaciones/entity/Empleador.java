package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "empleador")
public class Empleador {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleador;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(nullable = false)
    private  String nombre;

    @Column(nullable = false)
    private String apellido_1;

    @Column
    private String apellido_2;

    @Column
    private String direccion;

    @Column
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
