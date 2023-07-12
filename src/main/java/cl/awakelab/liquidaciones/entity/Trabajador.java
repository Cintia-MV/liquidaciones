package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_trabajador;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido_1;

    @Column
    private String apellido_2;

    @Column
    private String email;


    @ManyToOne(optional = false, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_prevision", nullable = false)
    private InstitucionPrevisional instPrevision;

    @ManyToOne(optional = false, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_salud", nullable = false)
    private InstitucionPrevisional instSalud;

    @Column(nullable = false)
    private long telefono;

    @OneToMany
    List<Liquidacion> listaTrabajadores;

    @ManyToMany(mappedBy = "trabajadores")
    private List<Empleador> listaEmpleadores;


}
