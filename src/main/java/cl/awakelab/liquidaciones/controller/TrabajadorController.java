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

import java.util.ArrayList;
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
    @Autowired
    IEmpleadorService objEmpleadorService;

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
        List<Empleador> empleador = objEmpleadorService.listarEmpleadores();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("empleador", empleador);
        model.addAttribute("trabajador", new Trabajador());
        return "formTrabajador";
    }

    @PostMapping("/crearTrabajador")
    public String crearTrabajador(@ModelAttribute Trabajador trabajador,
                                  @RequestParam("previsionId") int previsionId, //Aquí se está obteniendo una institución previsional (objeto de la clase "InstitucionPrevisional") mediante un servicio llamado "objPrevisionService" que busca la institución por su ID, utilizando el valor del parámetro "previsionId".
                                  @RequestParam("saludId") int saludId,
                                  @RequestParam("empleadorId") int empleadorId){
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionId); //Se establece la institución previsional obtenida en el paso 3 al atributo "instPrevision" del objeto "trabajador".
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        Empleador empleador = objEmpleadorService.buscarEmpleadorPorId(empleadorId);
        trabajador.setInstPrevision(prevision);
        trabajador.setInstSalud(salud);
        List<Empleador> listaEmpleadores = new ArrayList<>(); //Se crea una nueva lista llamada "listaEmpleadores" que almacenará objetos de la clase "Empleador".
        listaEmpleadores.add(empleador); //se agrega el objeto empleador a la lista empleadores
        trabajador.setListaEmpleadores(listaEmpleadores);
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
        List<Empleador> empleador = objEmpleadorService.listarEmpleadores();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("empleador", empleador);
        return "editarTrabajador";
    }

    @PostMapping("/actualizar/{idTrabajador}")
    public String actualizarTrabajador(@ModelAttribute Trabajador trabajador, @PathVariable int idTrabajador,
                                       @RequestParam("previsionId") int previsionId,
                                       @RequestParam("saludId") int saludId,
                                       @RequestParam("empleadorId") int empleadorId){
        InstitucionPrevisional prevision = objPrevisionService.buscarPrevisionPorId(previsionId);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(saludId);
        Empleador empleador = objEmpleadorService.buscarEmpleadorPorId(empleadorId);
        trabajador.setInstPrevision(prevision);
        trabajador.setInstSalud(salud);
        List<Empleador> listaEmpleadores = new ArrayList<>(); //Se crea una nueva lista llamada "listaEmpleadores" que almacenará objetos de la clase "Empleador".
        listaEmpleadores.add(empleador); //se agrega el objeto empleador a la lista empleadores
        trabajador.setListaEmpleadores(listaEmpleadores);
        objTrabajadorService.actualizarTrabajador(trabajador, idTrabajador);
        return "redirect:/trabajador";
    }

    //ELIMIAR TRABAJADOR
    @PostMapping("/eliminar/{idTrabajador}")
    public String eliminarTrabajadorPorId(@PathVariable int idTrabajador){
        objTrabajadorService.eliminarTrabajador(idTrabajador);
        return "redirect:/trabajador";
    }
}
