package EjercicioPractico2_LucianoSeravalliLeon.service;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Usuario;
import EjercicioPractico2_LucianoSeravalliLeon.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CorreoService correoService;


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        boolean esNuevo = (usuario.getId() == null);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        if (esNuevo) {
            correoService.enviarCorreoBienvenida(usuarioGuardado.getEmail(), usuarioGuardado.getNombre());
        }

        return usuarioGuardado;
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombre(nombreRol);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }
}