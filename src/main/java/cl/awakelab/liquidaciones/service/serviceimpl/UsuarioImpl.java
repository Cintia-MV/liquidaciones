package cl.awakelab.liquidaciones.service.serviceimpl;

import cl.awakelab.liquidaciones.entity.Usuario;
import cl.awakelab.liquidaciones.repository.IUsuarioRepo;
import cl.awakelab.liquidaciones.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("usuarioImpl")
public class UsuarioImpl implements IUsuarioService{
    @Autowired //inyección de repositorio
    IUsuarioRepo objUsuarioRepo;

    @Override
    public Usuario crearUsuario(Usuario usuario){
        return objUsuarioRepo.save(usuario);
    }

    //Registrar usuario
    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return objUsuarioRepo.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios(){
        return objUsuarioRepo.findAll();
    }
    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        return objUsuarioRepo.findById(idUsuario).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }
    @Override
    public Usuario actualizarUsuario(Usuario usuarioActualizar, int idUsuario) {
        Usuario usuario = objUsuarioRepo.findById(idUsuario).orElseThrow(()->new NoSuchElementException("Usuario no encontrado"));
        usuario.setClave(usuarioActualizar.getClave());
        usuario.setNombre(usuarioActualizar.getNombre());
        usuario.setApellido1(usuarioActualizar.getApellido1());
        usuario.setApellido2(usuarioActualizar.getApellido2());
        usuario.setPerfil(usuarioActualizar.getPerfil());
        usuario.setEmail(usuarioActualizar.getEmail());
        usuario.setTelefono(usuarioActualizar.getTelefono());
        return objUsuarioRepo.save(usuario);
    }




    @Override
    public void eliminarUsuario2(int idUsuario) {
        objUsuarioRepo.deleteById(idUsuario);
    }
}
