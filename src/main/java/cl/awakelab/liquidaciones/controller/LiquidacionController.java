package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.*;
import cl.awakelab.liquidaciones.service.ILiquidacionService;
import cl.awakelab.liquidaciones.service.IPrevisionService;
import cl.awakelab.liquidaciones.service.ISaludService;
import cl.awakelab.liquidaciones.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/crearLiquidacion")
    public String mostrarFormLiquidacion(Model model){
        List<Trabajador> trabajador = objTrabajadorService.listarTrabajadores();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        List<InstitucionPrevisional> prevision = objPrevisionService.listarPrevision();
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("salud", salud);
        model.addAttribute("prevision", prevision);
        model.addAttribute("liquidacion", new Liquidacion());
        return "formLiquidacion";
    }

    @PostMapping("/crearLiquidacion")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion,
                                  @RequestParam("trabajadorId") int trabajadorId,
                                  @RequestParam("saludId") int saludId,
                                  @RequestParam("previsionId")int previsionId,
                                  Model model) {
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(trabajadorId);
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionId);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        liquidacion.setTrabajador(trabajador);
        liquidacion.setIdInstSalud(salud);
        liquidacion.setIdInstPrevisional(prevision);

        // Agregar los atributos al modelo
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("salud", salud);
        model.addAttribute("prevision", prevision);
        return "redirect:/liquidacion";
    }

    //ELIMINAR LIQUIDACION
    @PostMapping("/eliminar/{idLiquidacion}")
    public String eliminarLiquidacionPorId(@PathVariable int idLiquidacion){
        objLiquidacionService.eliminarLiquidacion(idLiquidacion);
        return "redirect:/liquidacion";
    }


}
