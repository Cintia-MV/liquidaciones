package cl.awakelab.liquidaciones.controller;

import cl.awakelab.liquidaciones.entity.Usuario;
import cl.awakelab.liquidaciones.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller //controlador de Spring
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired //inyección de dependencias de la interfaz IUsuarioService en la variable objUsuarioService.
    IUsuarioService objUsuarioService; //permite que el controlador utilice los métodos proporcionados por el servicio IUsuarioService.

    //Listar usuarios
    @GetMapping
    public String listarUsuarios(Model model){ //Model se utiliza para enviar datos a la vista para su representación en el lado del cliente.
        List<Usuario> listaUsuarios = objUsuarioService.listarUsuarios(); //Aquí se llama al método listarUsuarios() del servicio IUsuarioService que ha sido inyectado en el controlador previamente. La variable listaUsuarios almacena la lista de objetos Usuario devueltos por el servicio.
        model.addAttribute("usuarios", listaUsuarios); // Se agrega la lista de usuarios al modelo utilizando el método addAttribute() del objeto model. Esto hace que la lista de usuarios esté disponible para su uso en la vista con el nombre "usuarios".
        return "listarUsuarios";
    }

    //Crear usuario
    @GetMapping("/crearUsuario")
    public String mostrarFormularioCrearUsuario(Model model){
        return "formUsuario";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario){ //es proporcionado automáticamente por Spring. La anotación @ModelAttribute se utiliza para vincular los datos enviados en el formulario con el objeto Usuario que recibimos en el método.
        usuario.setFechaCreacion(LocalDateTime.now());
        objUsuarioService.crearUsuario(usuario); //se llama al método crearUsuario() del servicio IUsuarioService, que ha sido inyectado en el controlador previamente. El objeto Usuario que recibimos en el método se pasa como argumento a este método del servicio para guardar el nuevo usuario en la base de datos.
        return "redirect:/usuario";
    }

    //Registrar usuario sin inicio de sesion con el form registro
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model){
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute Usuario usuario){
        usuario.setFechaCreacion(LocalDateTime.now());
        objUsuarioService.registrarUsuario(usuario);
        return "redirect:/login";
    }

    //Actualizar usuario
    // Método para mostrar el formulario para buscar un usuario por su ID
    @GetMapping("/{idUsuario}")
    public String buscarUsuarioPorId(@PathVariable int idUsuario, Model model) { //se utiliza para capturar el valor del ID del usuario desde la URL y asignarlo a la variable idUsuario.
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(idUsuario); //Este método busca el usuario en la base de datos utilizando el ID proporcionado y devuelve un objeto Usuario
        model.addAttribute("usuario", usuario); //se agrega este objeto Usuario al modelo con el nombre "usuario" usando el método addAttribute() del objeto model
        return "redirect:/usuario";
    }

    // Método para mostrar el formulario de edición de un usuario por su ID
    @PostMapping("/editar/{idUsuario}")
    public String mostrarFormularioEditarUsuario(@PathVariable int idUsuario, Model model) {
        model.addAttribute("usuario", objUsuarioService.buscarUsuarioPorId(idUsuario)); //llamo al método buscarUsuarioPorId(idUsuario) del servicio IUsuarioService para obtener el objeto Usuario que se desea editar.
        return "editarUsuario";
    }

    // Método para actualizar un usuario
    @PostMapping("/actualizar/{idUsuario}")
    public String actualizarUsuario(@ModelAttribute Usuario usuario, @PathVariable int idUsuario) { //@ModelAttribute Usuario usuario y @PathVariable int idUsuario se utilizan para capturar el objeto Usuario con los datos actualizados y el ID del usuario desde la URL, respectivamente.
        usuario.setFechaCreacion(LocalDateTime.now());
        objUsuarioService.actualizarUsuario(usuario, idUsuario); //llamo al método actualizarUsuario(usuario, idUsuario) del servicio IUsuarioService, pasando el objeto Usuario actualizado y el ID del usuario para actualizar los datos en la base de datos.
        return "redirect:/usuario";
    }

    //Eliminar Usuario

    //Cuando el usuario confirma la eliminación del usuario desde la vista "eliminarUsuario", se ejecuta este método.
    @GetMapping("/{idUsuario}/eliminar")
    public String mostrarEliminarUsuario(@PathVariable int idUsuario, Model model){ //capturar el valor del ID del usuario desde la URL y asignarlo a la variable idUsuario
        Usuario usuarioEliminar = objUsuarioService.buscarUsuarioPorId(idUsuario); //Este método busca el usuario en la base de datos utilizando el ID proporcionado y devuelve un objeto Usuario que se desea eliminar.
        model.addAttribute("usuario", usuarioEliminar); //se agrega este objeto Usuario al modelo con el nombre "usuario" utilizando el método addAttribute() del objeto model
        return "eliminarUsuario"; //representa el nombre de la vista que se mostrará al usuario para confirmar la eliminación del usuario
    }

    @PostMapping("/eliminar/{idUsuario}")
    public String eliminarUsuarioPorId(@PathVariable int idUsuario) {
        objUsuarioService.eliminarUsuario2(idUsuario); //se pasa el id del usuario para eliminarlo de la base de datos
        return "redirect:/usuario";
    }



}
