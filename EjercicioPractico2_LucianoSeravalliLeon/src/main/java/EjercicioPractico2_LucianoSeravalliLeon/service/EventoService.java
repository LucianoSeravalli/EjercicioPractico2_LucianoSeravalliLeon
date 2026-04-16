package EjercicioPractico2_LucianoSeravalliLeon.service;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Evento;
import EjercicioPractico2_LucianoSeravalliLeon.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> buscarActivos() {
        return eventoRepository.findByAc2voTrue();
    }

    public List<Evento> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return eventoRepository.findByFechaBetween(inicio, fin);
    }

    public List<Evento> buscarPorNombre(String nombre) {
        return eventoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public long contarActivos() {
        return eventoRepository.countByAc2voTrue();
    }
}