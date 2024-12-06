package presentacion;


import javax.swing.*;
import java.awt.*;

import logica.Profesor;
import logica.Sistema;


public class PProfesor extends JPanel {
	private Profesor profesor;
	private Sistema sistema;
	private PCrearLearningPath pCrearLearningPath;
	
	public PProfesor(Profesor profesor, Sistema sistema, JFrame ventanaPrincipal) {
		this.profesor=profesor;
		this.sistema=sistema;
		setLayout(new BorderLayout());
		
		String nombreProfesor= profesor.getLogin();
		JPanel panelEtiquetas= new JPanel();
		panelEtiquetas.setLayout(new GridLayout(2,1));
		
		JLabel etiqueta= new JLabel("Bienvenido,  "+profesor.getLogin(), SwingConstants.CENTER);
		JLabel tipoUsuario= new JLabel("(profesor)", SwingConstants.CENTER);
		etiqueta.setFont(new Font ("Arial", Font.BOLD, 16));
		panelEtiquetas.add(etiqueta);
		panelEtiquetas.add(tipoUsuario);
		
		add(panelEtiquetas, BorderLayout.NORTH);
		
		JButton botonMostrar=new JButton("Ver detalles");
		botonMostrar.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "tipo de usuario: "+profesor.getTipoUsuario());
			
		});
		JButton botonVolver=new JButton("Cerrar sesión");
		botonVolver.addActionListener(e ->{
			ventanaPrincipal.setContentPane(new JPanel());
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});
	add(botonVolver, BorderLayout.SOUTH);
	
		JPanel panelLearningPaths= new JPanel();
		panelEtiquetas.setLayout(new GridLayout(2,1));

		JButton botonCrearLearningPath=new JButton("Crear Learning Path");
		botonCrearLearningPath.addActionListener(e ->{
			ventanaPrincipal.setContentPane(new PCrearLearningPath(sistema, ventanaPrincipal, profesor));
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});

	
		JButton botonVerLearningPaths=new JButton("Ver Learning Paths");
		botonVerLearningPaths.addActionListener(e ->{
			ventanaPrincipal.setContentPane(new PVerLearningPath(sistema, ventanaPrincipal, profesor));
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});
		
		
		
		panelLearningPaths.add(botonCrearLearningPath);
		panelLearningPaths.add(botonVerLearningPaths);
		
		add(panelLearningPaths, BorderLayout.CENTER);
		
	}
}
