package cl.awakelab.liquidaciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "institucion_prevision")
public class InstitucionPrevisional {
    @Id
    @Column(name = "id_inst_prevision",nullable = false)
    private int idInstPrevision;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column(name = "porc_dcto",nullable = false)
    private float porcDcto;

    @JsonIgnore
   @OneToMany(mappedBy = "instPrevision")
    List<Trabajador> listaTrabajadores;

    @JsonIgnore
   @OneToMany(mappedBy = "idInstPrevisional")
    List<Liquidacion> liquidacionesPrev;

}
