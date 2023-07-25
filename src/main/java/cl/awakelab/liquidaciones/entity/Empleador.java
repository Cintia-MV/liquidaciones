package cl.awakelab.liquidaciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column
    private long telefono;

    //Lo que está comentado era cómo lo tenía antes, por eso no poblaba la tabla intermedia
    /*@ManyToMany
    @JoinTable(name = "empl_trab", //especifica la tabla intermedia que se utilizará para almacenar la relación.
            joinColumns = @JoinColumn(name = "id_empleador", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_trabajador"))
    private List<Trabajador> trabajadores;*/

    //Relación muchos a muchos
    @ManyToMany(mappedBy = "listaEmpleadores")
    @JsonIgnore
    private List<Trabajador> trabajadores;
}
