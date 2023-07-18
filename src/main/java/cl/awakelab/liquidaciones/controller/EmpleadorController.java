package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Empleador;
import cl.awakelab.liquidaciones.repository.IEmpleadorRepo;
import cl.awakelab.liquidaciones.service.IEmpleadorService;
import cl.awakelab.liquidaciones.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
