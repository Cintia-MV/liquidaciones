package cl.awakelab.liquidaciones.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuCotroller {
    @GetMapping("/bienvenida")
    public String bienvenida(){
        return "bienvenida";
    }
}
