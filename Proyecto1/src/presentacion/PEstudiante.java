package presentacion;


import javax.swing.*;
import java.awt.*;

import logica.Estudiante;
import logica.Sistema;


public class PEstudiante extends JPanel {
	private Estudiante estudiante;
	private Sistema sistema;
	
	public PEstudiante(Estudiante estudiante, Sistema sistema, JFrame ventanaPrincipal) {
		this.estudiante=estudiante;
		this.sistema=sistema;
		setLayout(new BorderLayout());
		
		JPanel panelEtiquetas= new JPanel();
		panelEtiquetas.setLayout(new GridLayout(2,1));
		
		JLabel etiqueta= new JLabel("Bienvenido,  "+estudiante.getLogin(), SwingConstants.CENTER);
		JLabel tipoUsuario= new JLabel("(estudiante)", SwingConstants.CENTER);
		etiqueta.setFont(new Font ("Arial", Font.BOLD, 16));
		panelEtiquetas.add(etiqueta);
		panelEtiquetas.add(tipoUsuario);
		
		add(panelEtiquetas, BorderLayout.NORTH);
		
		JButton botonMostrar=new JButton("Ver detalles");
		botonMostrar.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "tipo de usuario: "+estudiante.getTipoUsuario());
			
		});
		JButton botonVolver=new JButton("Cerrar sesión");
		botonVolver.addActionListener(e ->{
			ventanaPrincipal.setContentPane(new JPanel());
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});
	add(botonVolver, BorderLayout.SOUTH);

}
}
