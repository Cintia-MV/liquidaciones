package cl.awakelab.liquidaciones.service;

import cl.awakelab.liquidaciones.entity.Trabajador;

import java.util.List;

public interface ITrabajadorService {
    List<Trabajador> listarTrabajadores();
    Trabajador crearTrabajador(Trabajador trabajador);
    Trabajador buscarTrabajadorId(int idTrabajador);
    Trabajador actualizarTrabajador(Trabajador trabajador, int idTrabajador);
    public void eliminarTrabajador(int idTrabajador);

}
