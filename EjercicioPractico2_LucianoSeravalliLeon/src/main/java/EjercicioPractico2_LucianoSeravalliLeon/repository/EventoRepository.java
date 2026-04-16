package EjercicioPractico2_LucianoSeravalliLeon.repository;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByAc2voTrue();

    List<Evento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    long countByAc2voTrue();
}