package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @Column(name = "id_trabajador",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrabajador;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido_1",length = 100, nullable = false)
    private String apellido1;

    @Column(name = "apellido_2",length = 100)
    private String apellido2;

    @Column(length = 100)
    private String email;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_prevision", nullable = false)
    private InstitucionPrevisional instPrevision;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_salud", nullable = false)
    private InstitucionSalud instSalud;

    @Column(nullable = false)
    private long telefono;

    @OneToMany(mappedBy = "trabajador")
    List<Liquidacion> listaLiquidacion;

    //Así estaba y no poblaba la tabla intermedia
    /*@ManyToMany(mappedBy = "trabajadores")
    private List<Empleador> listaEmpleadores;*/

    //Relacion muchos a muchos de la tabla intermedia
    @ManyToMany
    @JoinTable(name = "empl_trab", //especifica la tabla intermedia que se utilizará para almacenar la relación.
            joinColumns = @JoinColumn(name = "id_trabajador", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_empleador", nullable = false))
    private List<Empleador> listaEmpleadores;
}
