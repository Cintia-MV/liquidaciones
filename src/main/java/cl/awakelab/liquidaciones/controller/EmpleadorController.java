package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Empleador;
import cl.awakelab.liquidaciones.entity.Usuario;
import cl.awakelab.liquidaciones.service.IEmpleadorService;
import cl.awakelab.liquidaciones.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {
    @Autowired
    IEmpleadorService objEmpleadorService;
    @Autowired
    IUsuarioService objUsuarioService;

    //LISTAR EMPLEADOR
    @GetMapping
    public String listarEmpleadores(Model model){
        List<Empleador> listarEmpleador = objEmpleadorService.listarEmpleadores();
        model.addAttribute("empleadores", listarEmpleador);
        return "listarEmpleadores";
    }

    //CREAR EMPLEADOR
    @GetMapping("/crearEmpleador")
    public String mostrarFormCrearEmpleador(Model model){
        List<Usuario> usuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("empleador", new Empleador());
        return "formEmpleador";
    }

    @PostMapping("/crearEmpleador")
    public String crearEmpleador(@ModelAttribute Empleador empleador, @RequestParam("usuarioId") int usuarioId){
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(usuarioId);
        empleador.setUsuario(usuario);
        objEmpleadorService.crearEmpleador(empleador);
        return "redirect:/empleador";
    }

    //ACTUALIZAR EMPLEADOR
    // Método para mostrar el formulario para buscar un empleador por su ID
    @GetMapping("/{idEmpleador}")
    public String buscarEmpleadorPorId(@PathVariable int idEmpleador, Model model) {
        Empleador empleador = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        model.addAttribute("empleador", empleador);
        return "redirect:/empleador";
    }

    // Método para mostrar el formulario de edición de un empleador por su ID
    @PostMapping("/editar/{idEmpleador}")
    public String mostrarFormEditarEmpleador(@PathVariable int idEmpleador, Model model) {
        model.addAttribute("empleador", objEmpleadorService.buscarEmpleadorPorId(idEmpleador));
        List<Usuario> usuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "editarEmpleador";
    }
    @PostMapping("/actualizar/{idEmpleador}")
    public String actualizarEmpleador(@ModelAttribute Empleador empleador, @PathVariable int idEmpleador, @RequestParam("usuarioId") int usuarioId) {
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(usuarioId);
        empleador.setUsuario(usuario);
        objEmpleadorService.actualizarEmpleador(empleador, idEmpleador);
        return "redirect:/empleador";
    }

    //ELIMINAR EMPLEADOR

    //Cuando el usuario confirma la eliminación del empleador desde la vista "eliminarEmpleador", se ejecuta este método.
    @GetMapping("/{idEmpleador}/eliminar")
    public String mostrarEliminarEmpleador(@PathVariable int idEmpleador, Model model){
        Empleador empleadorEliminar = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        model.addAttribute("empleador", empleadorEliminar);
        return "eliminarEmpleador";
    }
    @PostMapping("/eliminar/{idEmpleador}")
    public String eliminarEmpleadorPorId(@PathVariable int idEmpleador) {
        objEmpleadorService.eliminarEmpleador(idEmpleador);
        return "redirect:/empleador";
    }
}
