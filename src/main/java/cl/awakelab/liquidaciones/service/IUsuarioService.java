package cl.awakelab.liquidaciones.service;
import cl.awakelab.liquidaciones.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios (); //Su función es obtener y devolver una lista con todos los usuarios existentes en la base de datos
    Usuario crearUsuario(Usuario usuario); //Este método recibe un objeto Usuario como parámetro y debe devolver un objeto Usuario. Su función es tomar el objeto Usuario pasado como parámetro y registrar un nuevo usuario en la base de datos.
    Usuario registrarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int idUsuario); //Este método recibe un entero idUsuario como parámetro y debe devolver un objeto Usuario. Su función es buscar y obtener un usuario específico en la base de datos utilizando el ID proporcionado.
    Usuario actualizarUsuario(Usuario usuario, int idUsuario); //Este método recibe dos parámetros, un objeto Usuario y un entero idUsuario, y debe devolver un objeto Usuario. Su función es tomar el objeto Usuario pasado como parámetro, buscar al usuario correspondiente en la base de datos utilizando el idUsuario, y luego actualizar sus datos con la información proporcionada en el objeto Usuario.
    public void eliminarUsuario2(int idUsuario);
}
