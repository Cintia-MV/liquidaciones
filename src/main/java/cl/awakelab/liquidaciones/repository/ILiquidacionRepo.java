package cl.awakelab.liquidaciones.repository;

import cl.awakelab.liquidaciones.entity.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILiquidacionRepo extends JpaRepository<Liquidacion, Long> {
}
