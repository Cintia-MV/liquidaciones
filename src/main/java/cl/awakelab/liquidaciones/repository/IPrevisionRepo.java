package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.InstitucionPrevisional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrevisionRepo extends JpaRepository<InstitucionPrevisional, Integer> {
}
