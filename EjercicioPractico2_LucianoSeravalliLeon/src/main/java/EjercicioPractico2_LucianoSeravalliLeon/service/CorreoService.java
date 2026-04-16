
package EjercicioPractico2_LucianoSeravalliLeon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String destino, String nombre) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destino);
        mensaje.setSubject("Bienvenido a la plataforma de eventos");
        mensaje.setText("Hola " + nombre + ", tu usuario fue creado correctamente.");
        mailSender.send(mensaje);
    }
}