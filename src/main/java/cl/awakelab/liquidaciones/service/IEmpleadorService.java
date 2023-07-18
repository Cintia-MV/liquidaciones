package cl.awakelab.liquidaciones.service;

import cl.awakelab.liquidaciones.controller.EmpleadorController;
import cl.awakelab.liquidaciones.entity.Empleador;

import java.util.List;

public interface IEmpleadorService {
    List<Empleador> listarEmpleador();
    Empleador crearEmpleador(Empleador empleador);
    Empleador registrarUsuario(Empleador empleador);
    Empleador buscarEmpleadorporId(int idUsuario);
    Empleador actualizarEmpleador(Empleador empleador, int idEmpleador);
    public void eliminarUsuario(int idEmpleador);
}
