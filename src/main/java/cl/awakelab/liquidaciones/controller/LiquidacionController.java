package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Liquidacion;
import cl.awakelab.liquidaciones.service.ILiquidacionService;
import cl.awakelab.liquidaciones.service.IPrevisionService;
import cl.awakelab.liquidaciones.service.ISaludService;
import cl.awakelab.liquidaciones.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/liquidacion")
public class LiquidacionController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @Autowired
    ISaludService objSaludService;
    @Autowired
    IPrevisionService objPrevisionService;
    @Autowired
    ILiquidacionService objLiquidacionService;

    @GetMapping
    public String listarLiquidaciones(Model model){
        List<Liquidacion> listaLiquidaciones = objLiquidacionService.listarLiquidaciones();
        model.addAttribute("liquidaciones", listaLiquidaciones);
        return "listarLiquidaciones";
    }




}
