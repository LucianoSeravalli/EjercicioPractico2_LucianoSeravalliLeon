package EjercicioPractico2_LucianoSeravalliLeon.repository;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol findByNombre(String nombre);
}