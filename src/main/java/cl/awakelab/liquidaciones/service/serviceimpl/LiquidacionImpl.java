package cl.awakelab.liquidaciones.service.serviceimpl;

import cl.awakelab.liquidaciones.entity.InstitucionPrevisional;
import cl.awakelab.liquidaciones.entity.InstitucionSalud;
import cl.awakelab.liquidaciones.entity.Liquidacion;
import cl.awakelab.liquidaciones.repository.ILiquidacionRepo;
import cl.awakelab.liquidaciones.service.ILiquidacionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("liquidacionImpl")
public class LiquidacionImpl implements ILiquidacionService {
    @Autowired
    ILiquidacionRepo objLiquidacionRepo;
    @Override
    public List<Liquidacion> listarLiquidaciones() {
        return objLiquidacionRepo.findAll();
    }

    @Override
    public Liquidacion crearLiquidacion(Liquidacion liquidacion) {
        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public Liquidacion buscarLiquidacionId(long idLiquidacion) {
        return objLiquidacionRepo.findById(idLiquidacion).orElseThrow(() ->new NoSuchElementException("Liquidacion no encontrada"));
    }

    @Override
    public Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar, long idLiquidacion) {
        Liquidacion liquidacion = objLiquidacionRepo.findById(idLiquidacion).orElseThrow(() -> new NoSuchElementException("Liquidación no encontrada"));
        liquidacion.setTrabajador(liquidacionActualizar.getTrabajador());
        liquidacion.setSueldoImponible(liquidacionActualizar.getSueldoImponible());
        liquidacion.setSueldoLiquido(liquidacionActualizar.getSueldoLiquido());
        liquidacion.setIdInstSalud(liquidacionActualizar.getIdInstSalud());
        liquidacion.setMontoInstSalud(liquidacionActualizar.getMontoInstSalud());
        liquidacion.setIdInstPrevisional(liquidacionActualizar.getIdInstPrevisional());
        liquidacion.setMontoInstPrevisional(liquidacionActualizar.getMontoInstPrevisional());
        liquidacion.setTotalDescuento(liquidacionActualizar.getTotalDescuento());
        liquidacion.setTotalHaberes(liquidacionActualizar.getTotalHaberes());
        liquidacion.setAnticipo(liquidacionActualizar.getAnticipo());
        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public void eliminarLiquidacion(long idLiquidacion) {
        objLiquidacionRepo.deleteById(idLiquidacion);
    }
}
