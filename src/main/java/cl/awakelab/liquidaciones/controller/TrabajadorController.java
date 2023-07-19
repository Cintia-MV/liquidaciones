package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Empleador;
import cl.awakelab.liquidaciones.entity.InstitucionPrevisional;
import cl.awakelab.liquidaciones.entity.InstitucionSalud;
import cl.awakelab.liquidaciones.entity.Trabajador;
import cl.awakelab.liquidaciones.service.IEmpleadorService;
import cl.awakelab.liquidaciones.service.IPrevisionService;
import cl.awakelab.liquidaciones.service.ISaludService;
import cl.awakelab.liquidaciones.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @Autowired
    IPrevisionService objPrevisionService;
    @Autowired
    ISaludService objSaludService;

    @GetMapping
    public String listarTrabajadores(Model model){
        List<Trabajador> listaTrabajadores = objTrabajadorService.listarTrabajadores();
        model.addAttribute("trabajadores", listaTrabajadores);
        return "listarTrabajadores";
    }

    @GetMapping("/crearTrabajador")
    public String mostrarFormCrearTrabajador(Model model){
        List<InstitucionPrevisional> prevision = objPrevisionService.listarPrevision();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("trabajador", new Trabajador());
        return "formTrabajador";
    }

    @PostMapping("/crearTrabajador")
    public String crearTrabajador(@ModelAttribute Trabajador trabajador,
                                  @RequestParam("previsionId") int previsionId,
                                  @RequestParam("saludId") int saludId){
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionId);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        trabajador.setInstPrevision(prevision);
        trabajador.setInstSalud(salud);
        objTrabajadorService.crearTrabajador(trabajador);
        return "redirect:/trabajador";

    }
}
