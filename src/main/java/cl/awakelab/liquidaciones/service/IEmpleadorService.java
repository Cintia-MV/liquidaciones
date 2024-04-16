package cl.awakelab.liquidaciones.service;

import cl.awakelab.liquidaciones.entity.Empleador;

import java.util.List;

public interface IEmpleadorService {
    List<Empleador> listarEmpleadores();
    Empleador crearEmpleador(Empleador empleador);
    Empleador buscarEmpleadorPorId(int idUsuario);
    Empleador actualizarEmpleador(Empleador empleador, int idEmpleador);
    public void eliminarEmpleador(int idEmpleador);
}
