package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario,Integer>{ //integer corresponde al tipo de dato que tiene mi primary key

}
