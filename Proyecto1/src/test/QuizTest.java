package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Quiz;
import logica.Actividad;

public class QuizTest {
	private Quiz quiz1;
	
	@BeforeEach
	void setUp() {
		quiz1= new Quiz("quiz 1", "suma y resta de polinomios", "evaluar parcialmente los temas vistos en clase", "facil", 25, "2024-12-12", false, null, "1+1", 4, <"a=1"+"b=2"+"c=3"+"d=4">, 1);
	}

}
