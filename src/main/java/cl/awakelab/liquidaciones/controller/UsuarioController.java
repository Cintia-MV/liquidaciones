package cl.awakelab.liquidaciones.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @GetMapping(value = "/hola")
    public String holaMundo() {
        return "Hola mundo";
    }
}
