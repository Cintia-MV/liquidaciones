package cl.awakelab.liquidaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "institucion_prevision")
public class InstitucionPrevisional {
    @Id
    @Column(nullable = false)
    private int id_inst_prevision;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private float porc_dcto;

   @OneToMany(mappedBy = "instPrevision")
    List<Trabajador> listaTrabajadores;

   @OneToMany(mappedBy = "idInstPrevision")
    List<Liquidacion> liquidacionesPrev;

}
