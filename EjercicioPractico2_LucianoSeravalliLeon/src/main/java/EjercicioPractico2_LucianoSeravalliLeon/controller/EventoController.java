package EjercicioPractico2_LucianoSeravalliLeon.controller;

import EjercicioPractico2_LucianoSeravalliLeon.domain.Evento;
import EjercicioPractico2_LucianoSeravalliLeon.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("eventos", eventoService.listarEventos());
        return "eventos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("evento", new Evento());
        return "eventos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Evento evento) {
        eventoService.guardar(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("evento", eventoService.obtenerPorId(id));
        return "eventos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        eventoService.eliminar(id);
        return "redirect:/eventos";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("evento", eventoService.obtenerPorId(id));
        return "eventos/detalle";
    }

    @GetMapping("/consultas")
    public String consultas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fin,
            Model model) {

        if (nombre != null && !nombre.isBlank()) {
            model.addAttribute("resultados", eventoService.buscarPorNombre(nombre));
        } else if (inicio != null && fin != null && !inicio.isBlank() && !fin.isBlank()) {
            model.addAttribute("resultados",
                    eventoService.buscarPorRangoFechas(LocalDate.parse(inicio), LocalDate.parse(fin)));
        } else {
            model.addAttribute("resultados", eventoService.buscarActivos());
        }

        model.addAttribute("totalActivos", eventoService.contarActivos());
        return "eventos/consultas";
    }
}