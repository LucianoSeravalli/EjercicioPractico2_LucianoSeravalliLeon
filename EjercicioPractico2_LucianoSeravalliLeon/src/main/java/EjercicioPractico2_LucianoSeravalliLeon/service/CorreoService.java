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
        mensaje.setSubject("Bienvenido a EventosPlus");
        mensaje.setText(
                "Hola " + nombre + ",\n\n"
                + "Bienvenido(a) a EventosPlus.\n"
                + "Nos complace informarte que tu cuenta ha sido creada exitosamente.\n\n"
                + "Ya puedes iniciar sesión en la plataforma para gestionar eventos y consultar información.\n\n"
                + "Atentamente,\n"
                + "Equipo de EventosPlus"
        );

        mailSender.send(mensaje);
    }
}
