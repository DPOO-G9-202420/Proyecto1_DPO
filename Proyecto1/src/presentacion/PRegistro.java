package presentacion;

import logica.Sistema;

import javax.swing.*;
import java.awt.*;

public class PRegistro extends JPanel {
    public PRegistro(Sistema sistema, JFrame ventanaPrincipal) {
        setLayout(new GridLayout(5, 2));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblTipo = new JLabel("Tipo de Usuario:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"estudiante", "profesor"});

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String password = new String(txtPassword.getPassword());
            String tipo = (String) comboTipo.getSelectedItem();

            sistema.registrarUsuario(usuario, password, tipo);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
            ventanaPrincipal.setContentPane(new VentPrincipal(sistema).getContentPane());
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        btnCancelar.addActionListener(e -> {
        	// Regresar a la pantalla principal
            ventanaPrincipal.setContentPane(new VentPrincipal(sistema).getContentPane());
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        add(lblUsuario);
        add(txtUsuario);
        add(lblPassword);
        add(txtPassword);
        add(lblTipo);
        add(comboTipo);
        add(btnRegistrar);
        add(btnCancelar);
    }
}

