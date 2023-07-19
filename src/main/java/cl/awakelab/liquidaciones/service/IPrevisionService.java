package cl.awakelab.liquidaciones.service;

import cl.awakelab.liquidaciones.entity.InstitucionPrevisional;

import java.util.List;

public interface IPrevisionService {
    List<InstitucionPrevisional> listarPrevision();
    InstitucionPrevisional buscarPrevisionPorId(int idInstPrevision);
}
