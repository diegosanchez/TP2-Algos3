package unitarios.casillerosTest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.Jugador;
import modelo.Tablero;
import modelo.casilleros.Avance;
import modelo.casilleros.Salida;
import modelo.excepciones.ExcepcionCapitalInsuficiente;
import modelo.excepciones.ExcepcionJugadorNoRegistrado;
import modelo.excepciones.ExcepcionTerrenoOcupado;
import modelo.excepciones.ExcepcionJugadorPreso;

public class AvanceTest {
	
	@Test
	public void test01unAvanceDinamicoMueveAlJugadorQueCaeEnElla() throws ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente, ExcepcionJugadorPreso, ExcepcionJugadorNoRegistrado{
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		Avance unAvance = new Avance();
			
		listaJugadores.add(unJugador);		
		unAvance.caer(unJugador,unTablero, 4);
		
		Assert.assertNotEquals((new Salida()).getClass(), unTablero.getUbicacion(unJugador).getClass());
		
	}
	

}
