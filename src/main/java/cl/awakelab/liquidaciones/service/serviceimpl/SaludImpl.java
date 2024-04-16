package cl.awakelab.liquidaciones.service.serviceimpl;

import cl.awakelab.liquidaciones.entity.InstitucionSalud;
import cl.awakelab.liquidaciones.repository.ISaludRepo;
import cl.awakelab.liquidaciones.service.ISaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaludImpl implements ISaludService {
    @Autowired
    ISaludRepo objSaludRepo;
    @Override
    public List<InstitucionSalud> listarSalud() {
        return objSaludRepo.findAll();
    }

    public InstitucionSalud buscarSaludPorId(int idInstSalud){
        return objSaludRepo.findById(idInstSalud).orElseThrow(() -> new NoSuchElementException("Institucion no encontrada"));
    }
}
