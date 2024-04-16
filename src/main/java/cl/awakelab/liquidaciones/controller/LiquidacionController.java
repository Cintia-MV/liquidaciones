package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.*;
import cl.awakelab.liquidaciones.service.ILiquidacionService;
import cl.awakelab.liquidaciones.service.IPrevisionService;
import cl.awakelab.liquidaciones.service.ISaludService;
import cl.awakelab.liquidaciones.service.ITrabajadorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return "formLiquidacion";
    }

    @PostMapping("/crearLiquidacion")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion,
                                  @RequestParam("trabajadorId") int trabajadorId,
                                  @RequestParam("saludId") int saludId,
                                  @RequestParam("previsionId")int previsionId) {
        liquidacion.setPeriodo(LocalDate.now());
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(trabajadorId);
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionId);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        liquidacion.setTrabajador(trabajador);
        liquidacion.setIdInstSalud(salud);
        liquidacion.setIdInstPrevisional(prevision);

        objLiquidacionService.crearLiquidacion(liquidacion);
        return "redirect:/liquidacion";
    }

    //ACTUALIZAR LIQUIDACION
    @GetMapping("/{idLiquidacion}")
    public String buscarTrabajadorPorId(@PathVariable int idLiquidacion, Model model){
        Liquidacion liquidacion = objLiquidacionService.buscarLiquidacionId(idLiquidacion);
        model.addAttribute("liquidacion", liquidacion);
        return "redirect:/liquidacion";
    }

    @PostMapping("/editar/{idLiquidacion}")
    public String mostrarFormLiquidacion(@PathVariable int idLiquidacion, Model model){
        model.addAttribute("liquidacion", objLiquidacionService.buscarLiquidacionId(idLiquidacion));
        List<InstitucionPrevisional> prevision = objPrevisionService.listarPrevision();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        List<Trabajador> trabajador = objTrabajadorService.listarTrabajadores();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("trabajador", trabajador);
        return "editarLiquidacion";
    }

    @PostMapping("/actualizar/{idLiquidacion}")
    public String actualizarTrabajador(@ModelAttribute Liquidacion liquidacion, @PathVariable int idLiquidacion,
                                       @RequestParam("previsionId") int previsionaId,
                                       @RequestParam("saludId") int saludId,
                                       @RequestParam("trabajadorId") int trabajadorId){
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionaId);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(trabajadorId);
        liquidacion.setTrabajador(trabajador);
        liquidacion.setIdInstPrevisional(prevision);
        liquidacion.setIdInstSalud(salud);
        objLiquidacionService.actualizarLiquidacion(liquidacion, idLiquidacion);

        return "redirect:/liquidacion";
    }



    //ELIMINAR LIQUIDACION
    @PostMapping("/eliminar/{idLiquidacion}")
    public String eliminarLiquidacionPorId(@PathVariable int idLiquidacion){
        objLiquidacionService.eliminarLiquidacion(idLiquidacion);
        return "redirect:/liquidacion";
    }


}
