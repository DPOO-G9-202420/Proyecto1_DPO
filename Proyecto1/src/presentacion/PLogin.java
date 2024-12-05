package presentacion;

import logica.Estudiante;
import logica.Profesor;
import logica.Sistema;
import logica.Usuario;

import javax.swing.*;
import java.awt.*;

public class PLogin extends JPanel {
	private PEstudiante pantallaEstudiante;
	private PProfesor pantallaProfesor;
	
    public PLogin(Sistema sistema, JFrame ventanaPrincipal) {
        setLayout(new GridLayout(4, 1));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnCancelar = new JButton("Cancelar");

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String password = new String(txtPassword.getPassword());

            Usuario user = sistema.iniciarSesion(usuario, password);
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            } else {
                if (user instanceof Estudiante) {
                    ventanaPrincipal.setContentPane(new PEstudiante((Estudiante) user, sistema, ventanaPrincipal));
                } else if (user instanceof Profesor) {
                    ventanaPrincipal.setContentPane(new PProfesor((Profesor) user, sistema, ventanaPrincipal));
                }
                ventanaPrincipal.revalidate();
                ventanaPrincipal.repaint();
            }
        });

        btnCancelar.addActionListener(e -> {
            ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.add(new VentPrincipal(sistema));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        add(lblUsuario);
        add(txtUsuario);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnCancelar);
    }
}

