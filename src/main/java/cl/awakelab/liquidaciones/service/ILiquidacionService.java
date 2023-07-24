package cl.awakelab.liquidaciones.service;

import cl.awakelab.liquidaciones.entity.Liquidacion;

import java.util.List;

public interface ILiquidacionService {
    List<Liquidacion> listarLiquidaciones();
    Liquidacion crearLiquidacion(Liquidacion liquidacion);
    Liquidacion buscarLiquidacionId(long idLiquidacion);
    Liquidacion actualizarLiquidacion(Liquidacion liquidacion, long idLiquidacion);
    public void eliminarLiquidacion(long idLiquidacion);
}
