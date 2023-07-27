package cl.awakelab.liquidaciones.restController;

import cl.awakelab.liquidaciones.entity.Liquidacion;
import cl.awakelab.liquidaciones.service.ILiquidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/liquidacion", headers = "Accept=Application/json")
public class LiquidacionRestController {
    @Autowired
    ILiquidacionService objLiquidacionService;

    @GetMapping
    public List<Liquidacion> listarLiquidaciones(){
        return objLiquidacionService.listarLiquidaciones();
    }

    @GetMapping("/{idLiquidacion}")
    public Liquidacion buscarLiquidacionPorId(@PathVariable long idLiquidacion){
        return objLiquidacionService.buscarLiquidacionId(idLiquidacion);
    }
}
