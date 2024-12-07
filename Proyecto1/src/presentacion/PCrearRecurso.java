package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.learningPath;
import logica.RevisarRecurso;
import logica.Sistema;

public class PCrearRecurso extends JPanel {
    public PCrearRecurso(Sistema sistema, learningPath learningPath) {
        setLayout(new GridLayout(4, 2, 10, 10));

        // Campos para ingresar datos del recurso
        add(new JLabel("Nombre del Recurso:"));
        JTextField txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Descripción del Recurso:"));
        JTextField txtDescripcion = new JTextField();
        add(txtDescripcion);
        
        JLabel lblDificultad = new JLabel("Nivel de Dificultad:");
        JComboBox<String> comboDificultad = new JComboBox<>(new String[]{"Fácil", "Intermedio", "Difícil"});
        add(lblDificultad);
        add(comboDificultad);
        
        JLabel lblDuracion = new JLabel("Duración (minutos):");
        JTextField txtDuracion = new JTextField();
        add(lblDuracion);
        add(txtDuracion);
        
        JLabel lblOpcional = new JLabel("Es opcional?");
        JComboBox<String> comboOpcional = new JComboBox<>(new String[]{"SI", "NO"});
        add(lblOpcional);
        add(comboOpcional);
        
        JLabel lblEnlace = new JLabel("URL del Recurso:");
        JTextField txtEnlace = new JTextField();
        add(lblEnlace);
        add(txtEnlace);
        
        JLabel lblTipoR = new JLabel("Tipo de recurso");
        JTextField txtTipoR = new JTextField();
        add(lblTipoR);
        add(txtTipoR);

        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        // Espacio vacío para centrar el botón
        add(new JLabel(""));

        btnGuardar.addActionListener(e -> {
        	boolean esOpcional;
        	String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            String dificultad = (String) comboDificultad.getSelectedItem();
            int duracion = Integer.parseInt(txtDuracion.getText());
            String esOpcionalS = (String) comboOpcional.getSelectedItem();
            if (esOpcionalS.equals("SI")) {
            	esOpcional=true;
            }
            else {esOpcional=false;}
            
            String tipoR = txtTipoR.getText();
            String enlace = txtEnlace.getText();
            
            RevisarRecurso recurso = new RevisarRecurso(nombre, descripcion,"objetivo", dificultad,duracion, "fecha", esOpcional, null, tipoR,enlace);
            learningPath.agregarActividad(recurso);
            sistema.guardarActividadEnCSV(recurso,learningPath.getTitulo());

            JOptionPane.showMessageDialog(this, "Recurso agregado exitosamente.");
        });
    }
}
