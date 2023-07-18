package cl.awakelab.liquidaciones.service.serviceimpl;

import cl.awakelab.liquidaciones.entity.Empleador;
import cl.awakelab.liquidaciones.entity.Usuario;
import cl.awakelab.liquidaciones.repository.IEmpleadorRepo;
import cl.awakelab.liquidaciones.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("empleadorImpl")
public class EmpleadorImpl implements IEmpleadorService {
    @Autowired
    IEmpleadorRepo objEmpleadorRepo;
    @Override
    public List<Empleador> listarEmpleador(){
        return objEmpleadorRepo.findAll();
    }

    @Override
    public Empleador crearEmpleador(Empleador empleador) {
        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public Empleador registrarUsuario(Empleador empleador) {
        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public Empleador buscarEmpleadorporId(int idEmpleador) {
        return objEmpleadorRepo.findById(idEmpleador).orElseThrow(() -> new NoSuchElementException("Empleador no encontrado"));
    }

    @Override
    public Empleador actualizarEmpleador(Empleador empleadorActualizar, int idEmpleador) {
       Empleador empleador = objEmpleadorRepo.findById(idEmpleador).orElseThrow(() -> new NoSuchElementException("Empleador no encontrado"));
       empleador.setRun(empleadorActualizar.getRun());
       empleador.setNombre(empleadorActualizar.getNombre());
       empleador.setApellido1(empleadorActualizar.getApellido1());
       empleador.setApellido2(empleadorActualizar.getApellido2());
       empleador.setDireccion(empleadorActualizar.getDireccion());
       empleador.setEmail(empleadorActualizar.getEmail());
       empleador.setUsuario(empleadorActualizar.getUsuario());
       empleador.setTelefono(empleadorActualizar.getTelefono());
       empleador.setTrabajadores(empleadorActualizar.getTrabajadores());
       return objEmpleadorRepo.save(empleador);
    }

    @Override
    public void eliminarUsuario(int idEmpleador) {
        objEmpleadorRepo.deleteById(idEmpleador);
    }
}
