package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Empleador;
import cl.awakelab.liquidaciones.entity.Usuario;
import cl.awakelab.liquidaciones.repository.IEmpleadorRepo;
import cl.awakelab.liquidaciones.service.IEmpleadorService;
import cl.awakelab.liquidaciones.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {
    @Autowired
    IEmpleadorService objEmpleadorService;

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
        return "formEmpleador";
    }

    @PostMapping("/crearEmpleador")
    public String crearEmpleador(@ModelAttribute Empleador empleador){ //es proporcionado automáticamente por Spring. La anotación @ModelAttribute se utiliza para vincular los datos enviados en el formulario con el objeto Usuario que recibimos en el método.
        objEmpleadorService.crearEmpleador(empleador); //se llama al método crearUsuario() del servicio IUsuarioService, que ha sido inyectado en el controlador previamente. El objeto Usuario que recibimos en el método se pasa como argumento a este método del servicio para guardar el nuevo usuario en la base de datos.
        return "redirect:/empleador";
    }


}
