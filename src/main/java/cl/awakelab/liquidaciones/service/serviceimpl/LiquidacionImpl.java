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
    @Transactional
    public Liquidacion crearLiquidacion(Liquidacion liquidacion) {
        // Obtener el sueldo imponible ingresado por el usuario en el formulario
        int sueldoImponible = liquidacion.getSueldoImponible();

        // Obtener la AFP del trabajador desde la entidad Liquidacion
        InstitucionPrevisional afpTrabajador = liquidacion.getIdInstPrevisional();

        // Obtener el porcentaje de descuento de AFP correspondiente a la AFP del trabajador desde la tabla "institucion_prevision"
        double porcentajeAFP = afpTrabajador.getPorcDcto();

        // Calcular el monto de AFP
        int montoAFP = (int) (sueldoImponible * (porcentajeAFP / 100.0)); // Dividimos por 100 para convertir el porcentaje a decimal

        //CALCULAR DESCUENTO DE SALUD

        // Obtener la institución de salud del trabajador desde la entidad Liquidacion
        InstitucionSalud saludTrabajador = liquidacion.getIdInstSalud();

        // Obtener el porcentaje de descuento de salud correspondiente a la institución de salud del trabajador desde la tabla "institucion_salud"
        double porcentajeSalud = saludTrabajador.getPorcDcto();

        // Calcular el monto de descuento de salud
        int montoSalud = (int) (sueldoImponible * (porcentajeSalud / 100.0)); // Dividimos por 100 para convertir el porcentaje a decimal

        // Calcular el total de descuentos sumando el descuento de AFP y el descuento de salud
        int totalDescuento = montoAFP + montoSalud;

        // Obtener el anticipo ingresado por el usuario en el formulario
        int anticipo = liquidacion.getAnticipo();

        // Calcular el sueldo líquido restando los descuentos y el anticipo del total de haberes
        int totalHaberes = sueldoImponible;
        int sueldoLiquido = totalHaberes - totalDescuento - anticipo;

        // Asignar los cálculos a la liquidación
        liquidacion.setMontoInstSalud(montoSalud);
        liquidacion.setMontoInstPrevisional(montoAFP);
        liquidacion.setTotalDescuento(totalDescuento);
        liquidacion.setTotalHaberes(totalHaberes);
        liquidacion.setAnticipo(anticipo);
        liquidacion.setSueldoLiquido(sueldoLiquido);

        // Guardar la liquidación en la base de datos
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
        liquidacion.setPeriodo(liquidacionActualizar.getPeriodo());
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
