package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Indica que esta interfaz es un repositorio de datos y Spring Data JPA se encargará de proporcionar la implementación de los métodos de esta interfaz
public interface IUsuarioRepo extends JpaRepository<Usuario,Integer>{ //El primer parámetro, Usuario, es el tipo de entidad con la que trabajará este repositorio.
                                                                      //El segundo parámetro, Integer, indica el tipo de dato de la clave primaria de la entidad Usuario.
}
