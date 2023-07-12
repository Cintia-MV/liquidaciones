package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "institucion_salud")
public class InstitucionSalud {
    @Id
    @Column(nullable = false)
    private int id_inst_salud;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private float porc_dcto;

    @OneToMany(mappedBy = "instSalud")
    List<Trabajador> listaTrabajadores;

    @OneToMany(mappedBy = "idInstSalud")
    List<Liquidacion> liquidacionesSalud;

}
