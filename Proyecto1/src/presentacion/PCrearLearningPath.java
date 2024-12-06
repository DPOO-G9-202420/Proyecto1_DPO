package presentacion;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

import logica.Profesor;
import logica.Sistema;
import logica.Usuario;

public class PCrearLearningPath extends JPanel {
	private PProfesor pantallaProfesor;
	private Sistema sistema;
	private Profesor profesor;
	
	public PCrearLearningPath(Sistema sistema, JFrame ventanaPrincipal, Profesor profesor) {
		this.sistema=sistema;
		this.profesor=profesor;
		setLayout(new BorderLayout());
		
		JLabel etiqueta= new JLabel("Pantalla de crear Learning Path", SwingConstants.CENTER);
		etiqueta.setFont(new Font ("Arial", Font.BOLD, 18));
		add(etiqueta, BorderLayout.NORTH);
		
		JPanel panelEspacios= new JPanel();
		panelEspacios.setLayout(new GridLayout(4,2));
		
		
		JLabel lblTitulo= new JLabel("Título del Learning Path");
		JTextField txtTitulo= new JTextField();
		panelEspacios.add(lblTitulo);
		panelEspacios.add(txtTitulo);
		
		
		JLabel lblDescripcion= new JLabel("Descripción del Learning Path");
		JTextField txtDescripcion= new JTextField();
		panelEspacios.add(lblDescripcion);
		panelEspacios.add(txtDescripcion);
	
		
		JLabel lblNivel= new JLabel("Nivel de dificultad (de 1 a 5)");
		JTextField txtNivel= new JTextField(10);
		panelEspacios.add(lblNivel);
		panelEspacios.add(txtNivel);
	
		add(panelEspacios, BorderLayout.CENTER);
		
		JButton btnCrear= new JButton("Crear");
		btnCrear.addActionListener(e->{
			String Titulo =txtTitulo.getText();
			String Descripcion=txtDescripcion.getText();
			String Nivel=txtNivel.getText();
			sistema.crearLearningPath(profesor, Titulo, Descripcion, Nivel);
			ventanaPrincipal.setContentPane(new PCrearLearningPath(sistema, ventanaPrincipal, profesor));
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});
		JButton btnVolver=new JButton("Volver");
		btnVolver.addActionListener(e->{
			ventanaPrincipal.setContentPane(new PProfesor(profesor, sistema, ventanaPrincipal));
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(btnCrear);
		panelBotones.add(btnVolver);
		
		add(panelBotones, BorderLayout.SOUTH);
		
	}
	
}
