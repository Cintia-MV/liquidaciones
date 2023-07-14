package cl.awakelab.liquidaciones.service;
import cl.awakelab.liquidaciones.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios ();
    Usuario crearUsuario(Usuario usuario);
    Usuario registrarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int idUsuario);
    Usuario actualizarUsuario(Usuario usuario, int idUsuario);
    Usuario actualizarUsuario2(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public void eliminarUsuario2(int idUsuario);
}
