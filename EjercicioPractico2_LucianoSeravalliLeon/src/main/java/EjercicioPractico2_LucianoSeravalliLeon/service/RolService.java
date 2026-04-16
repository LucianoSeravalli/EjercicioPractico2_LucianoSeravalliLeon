
package EjercicioPractico2_LucianoSeravalliLeon.service;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Rol;
import EjercicioPractico2_LucianoSeravalliLeon.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    public Rol buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
}