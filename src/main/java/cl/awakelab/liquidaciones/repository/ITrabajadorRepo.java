package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrabajadorRepo extends JpaRepository <Trabajador, Integer> {
}
