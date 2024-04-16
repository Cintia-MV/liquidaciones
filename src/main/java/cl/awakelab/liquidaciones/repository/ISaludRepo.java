package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.InstitucionSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaludRepo extends JpaRepository<InstitucionSalud, Integer> {
}
