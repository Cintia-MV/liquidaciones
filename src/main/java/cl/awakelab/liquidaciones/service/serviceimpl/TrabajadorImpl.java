package cl.awakelab.liquidaciones.service.serviceimpl;

import cl.awakelab.liquidaciones.entity.Trabajador;
import cl.awakelab.liquidaciones.repository.ITrabajadorRepo;
import cl.awakelab.liquidaciones.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("trabajadorImpl")
public class TrabajadorImpl implements ITrabajadorService {
    @Autowired
    ITrabajadorRepo objTrabajadorRepo;
    @Override
    public List<Trabajador> listarTrabajadores() {
        return objTrabajadorRepo.findAll();
    }

    @Override
    public Trabajador crearTrabajador(Trabajador trabajador) {
        trabajador.setListaEmpleadores(trabajador.getListaEmpleadores()); //No se está usando
        return objTrabajadorRepo.save(trabajador);
    }

    @Override
    public Trabajador buscarTrabajadorId(int idTrabajador) {
        return objTrabajadorRepo.findById(idTrabajador).orElseThrow(() -> new NoSuchElementException("Trabajador no encontrado"));
    }

    @Override
    public Trabajador actualizarTrabajador(Trabajador trabajadorActualizar, int idTrabajador) {
        Trabajador trabajador = objTrabajadorRepo.findById(idTrabajador).orElseThrow(()-> new NoSuchElementException("Trabajador no encontrado"));
        trabajador.setRun(trabajadorActualizar.getRun());
        trabajador.setNombre(trabajadorActualizar.getNombre());
        trabajador.setApellido1(trabajadorActualizar.getApellido1());
        trabajador.setApellido2(trabajadorActualizar.getApellido2());
        trabajador.setEmail(trabajadorActualizar.getEmail());
        trabajador.setInstPrevision(trabajadorActualizar.getInstPrevision());
        trabajador.setInstSalud(trabajadorActualizar.getInstSalud());
        trabajador.setTelefono(trabajadorActualizar.getTelefono());
        //quizas tenga que agregar algo más acá
        return objTrabajadorRepo.save(trabajador);
    }

    @Override
    public void eliminarTrabajador(int idTrabajador) {
        objTrabajadorRepo.deleteById(idTrabajador); ;
    }
}
