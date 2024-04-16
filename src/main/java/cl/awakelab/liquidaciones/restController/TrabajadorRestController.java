package cl.awakelab.liquidaciones.restController;

import cl.awakelab.liquidaciones.entity.Trabajador;
import cl.awakelab.liquidaciones.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trabajador", headers = "Accept=Application/json")
public class TrabajadorRestController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @GetMapping
    public List<Trabajador> listarTrabajadores(){
        return objTrabajadorService.listarTrabajadores();
    }

    @GetMapping("/{idTrabajador}")
    public Trabajador buscarTrabajadorPorId(@PathVariable int idTrabajador) {
        return objTrabajadorService.buscarTrabajadorId(idTrabajador);
    }

    @PutMapping("/{idTrabajador}")
    public Trabajador actualizarTrabajador(@RequestBody Trabajador trabajadorActualizar,
                                           @PathVariable int idTrabajador) {
        return objTrabajadorService.actualizarTrabajador(trabajadorActualizar, idTrabajador);
    }
}



