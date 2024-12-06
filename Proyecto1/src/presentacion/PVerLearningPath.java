package presentacion;

import javax.swing.*;
import java.awt.*;

import logica.Profesor;
import logica.Sistema;
import logica.learningPath;

public class PVerLearningPath extends JPanel{
	private PProfesor pantallaProfesor;
	private Sistema sistema;
	
	public PVerLearningPath(Sistema sistema, JFrame ventanaPrincipal, Profesor profesor) {
		this.sistema=sistema;
		setLayout(new BorderLayout());
		
		JLabel etiqueta=new JLabel("Ver Learning Paths", SwingConstants.CENTER);
		etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
		add(etiqueta, BorderLayout.NORTH);
		
		java.util.List<learningPath> learningPaths= sistema.obtenerLearningPaths();
		JTextArea textArea= new JTextArea();
		textArea.setEditable(false);
		learningPaths.toString();
		for (learningPath learningPath: learningPaths) {
			textArea.append(learningPath.toString()+"\n\n");
		}
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		JButton btnVolver=new JButton ("Volver");
		btnVolver.addActionListener(e->{
			ventanaPrincipal.setContentPane(new PProfesor(profesor, sistema, ventanaPrincipal));
			ventanaPrincipal.revalidate();
			ventanaPrincipal.repaint();
		});
		add(btnVolver, BorderLayout.SOUTH);
	}
	
}
