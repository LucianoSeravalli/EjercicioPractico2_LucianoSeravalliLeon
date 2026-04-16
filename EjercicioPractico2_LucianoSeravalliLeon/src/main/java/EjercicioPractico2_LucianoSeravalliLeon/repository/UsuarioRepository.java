package EjercicioPractico2_LucianoSeravalliLeon.repository;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public List<Usuario> findByRolNombre(String nombre);
}