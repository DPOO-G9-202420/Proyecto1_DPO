package presentacion;

import javax.swing.*;
import java.awt.*;

import logica.Sistema;

public class VentPrincipal extends JFrame {
    private Sistema sistema;

    public VentPrincipal(Sistema sistema) {
        this.sistema = sistema;
        setTitle("Learning Path System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Mostrar pantalla inicial
        mostrarPantallaInicial();
    }

    private void mostrarPantallaInicial() {
        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new GridLayout(3, 1));

        JButton btnRegistro = new JButton("Registrar Usuario");
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnSalir = new JButton("Salir");

        btnRegistro.addActionListener(e -> mostrarPantallaRegistro());
        btnLogin.addActionListener(e -> mostrarPantallaLogin());
        btnSalir.addActionListener(e -> System.exit(0));

        panelInicio.add(btnRegistro);
        panelInicio.add(btnLogin);
        panelInicio.add(btnSalir);

        getContentPane().removeAll();
        add(panelInicio, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPantallaRegistro() {
        setContentPane(new PRegistro(sistema, this));
        revalidate();
        repaint();
    }

    private void mostrarPantallaLogin() {
        setContentPane(new PLogin(sistema, this));
        revalidate();
        repaint();
    }
    
    
}
