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

    //CREAR TRABAJADOR
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

    //ACTUALIZAR TRABAJADOR
    @GetMapping("/{idTrabajador}")
    public String buscarTrabajadorPorId(@PathVariable int idTrabajador, Model model){
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(idTrabajador);
        model.addAttribute("trabajador", trabajador);
        return "redirect:/trabajador";
    }

    @PostMapping("/editar/{idTrabajador}")
    public String mostrarFormTrabajador(@PathVariable int idTrabajador, Model model){
        model.addAttribute("trabajador", objTrabajadorService.buscarTrabajadorId(idTrabajador));
        List<InstitucionPrevisional> prevision = objPrevisionService.listarPrevision();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        return "editarTrabajador";
    }

    @PostMapping("/actualizar/{idTrabajador}")
    public String actualizarTrabajador(@ModelAttribute Trabajador trabajador, @PathVariable int idTrabajador,
                                       @RequestParam("previsionId") int previsionId,
                                       @RequestParam("saludId") int saludId){
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionId);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        trabajador.setInstPrevision(prevision);
        trabajador.setInstSalud(salud);
        objTrabajadorService.actualizarTrabajador(trabajador, idTrabajador);
        return "redirect:/trabajador";
    }

    //ELIMIAR TRABAJADOR
   /* @GetMapping("/{idTrabajador}/eliminar")
    public String mostrarEliminarTrabajador(@PathVariable int idTrabajador, Model model){
        Trabajador trabajadorEliminar = objTrabajadorService.buscarTrabajadorId(idTrabajador);
        model.addAttribute("trabajador", trabajadorEliminar);
        return "eliminarTrabajador";
    }*/

    @PostMapping("/eliminar/{idTrabajador}")
    public String eliminarTrabajadorPorId(@PathVariable int idTrabajador){
        objTrabajadorService.eliminarTrabajador(idTrabajador);
        return "redirect:/trabajador";
    }
}
