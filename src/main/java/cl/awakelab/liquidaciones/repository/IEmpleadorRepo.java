package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.Empleador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadorRepo extends JpaRepository<Empleador,Integer>{
}
