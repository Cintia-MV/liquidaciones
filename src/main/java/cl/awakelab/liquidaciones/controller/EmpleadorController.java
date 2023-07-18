package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Empleador;
import cl.awakelab.liquidaciones.entity.Usuario;
import cl.awakelab.liquidaciones.repository.IEmpleadorRepo;
import cl.awakelab.liquidaciones.service.IEmpleadorService;
import cl.awakelab.liquidaciones.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {
    @Autowired
    IEmpleadorService objEmpleadorService;
    @Autowired
    IUsuarioService objUsuarioService;

    //Listar empleador
    @GetMapping
    public String listarEmpleadores(Model model){
        List<Empleador> listarEmpleador = objEmpleadorService.listarEmpleador();
        model.addAttribute("empleadores", listarEmpleador);
        return "listarEmpleadores";
    }

    //Crear empleador
    @GetMapping("/crearEmpleador")
    public String mostrarFormCrearEmpleador(Model model){
        List<Usuario> usuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("empleador", new Empleador());
        return "formEmpleador";
    }

    @PostMapping("/crearEmpleador")
    public String crearEmpleador(@ModelAttribute Empleador empleador, @RequestParam("usuarioId") int usuarioId){ //es proporcionado automáticamente por Spring. La anotación @ModelAttribute se utiliza para vincular los datos enviados en el formulario con el objeto Usuario que recibimos en el método.
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(usuarioId);
        empleador.setUsuario(usuario);
        objEmpleadorService.crearEmpleador(empleador); //se llama al método crearUsuario() del servicio IUsuarioService, que ha sido inyectado en el controlador previamente. El objeto Usuario que recibimos en el método se pasa como argumento a este método del servicio para guardar el nuevo usuario en la base de datos.
        return "redirect:/empleador";
    }

    //Actualizar usuario
    // Método para mostrar el formulario para buscar un usuario por su ID
    @GetMapping("/{idEmpleador}")
    public String buscarEmpleadorPorId(@PathVariable int idEmpleador, Model model) { //se utiliza para capturar el valor del ID del usuario desde la URL y asignarlo a la variable idUsuario.
        Empleador empleador = objEmpleadorService.buscarEmpleadorporId(idEmpleador); //Este método busca el usuario en la base de datos utilizando el ID proporcionado y devuelve un objeto Usuario
        model.addAttribute("empleador", empleador); //se agrega este objeto Usuario al modelo con el nombre "usuario" usando el método addAttribute() del objeto model
        return "redirect:/empleador";
    }

    // Método para mostrar el formulario de edición de un usuario por su ID
    @PostMapping("/editar/{idEmpleador}")
    public String mostrarFormEditarEmpleador(@PathVariable int idEmpleador, Model model) {
        model.addAttribute("empleador", objEmpleadorService.buscarEmpleadorporId(idEmpleador)); //llamo al método buscarUsuarioPorId(idUsuario) del servicio IUsuarioService para obtener el objeto Usuario que se desea editar.
        List<Usuario> usuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "editarEmpleador";
    }

    @PostMapping("/actualizar/{idEmpleador}")
    public String actualizarEmpleador(@ModelAttribute Empleador empleador, @PathVariable int idEmpleador, @RequestParam("usuarioId") int usuarioId) { //@ModelAttribute Usuario usuario y @PathVariable int idUsuario se utilizan para capturar el objeto Usuario con los datos actualizados y el ID del usuario desde la URL, respectivamente.
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(usuarioId);
        empleador.setUsuario(usuario);
        objEmpleadorService.actualizarEmpleador(empleador, idEmpleador); //llamo al método actualizarUsuario(usuario, idUsuario) del servicio IUsuarioService, pasando el objeto Usuario actualizado y el ID del usuario para actualizar los datos en la base de datos.
        return "redirect:/empleador";
    }

}
