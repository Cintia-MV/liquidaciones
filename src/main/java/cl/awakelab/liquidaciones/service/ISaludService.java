package cl.awakelab.liquidaciones.service;

import cl.awakelab.liquidaciones.entity.InstitucionSalud;

import java.util.List;

public interface ISaludService {
    List<InstitucionSalud> listarSalud();
    InstitucionSalud buscarSaludPorId(int idInstSalud);
}
