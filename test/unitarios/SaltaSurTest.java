package unitarios;

import org.junit.Test;

import modelo.Jugador;
import modelo.casilleros.Barrio;
import modelo.casilleros.SaltaSur;
import modelo.excepciones.ExcepcionTerrenoOcupado;

import org.junit.Assert;

public class SaltaSurTest {
	
	private static final double DELTA = 1e-15;

	@Test
	public void test01ComprarTerrenoNoOcupadoDescuentaPrecioCorrectoDelCapitalDelJugador() throws ExcepcionTerrenoOcupado {
		
		Jugador jugador = new Jugador();
		Barrio barrio = new SaltaSur();
		
		barrio.comprarTerreno(jugador);
		
		Assert.assertEquals(23000, 100000 - jugador.balance(), DELTA);
	}
	
	@Test (expected = ExcepcionTerrenoOcupado.class)
	public void test02ComprarTerrenoOcupadoArrojaExcepcion() throws ExcepcionTerrenoOcupado {
		
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
		Barrio barrio = new SaltaSur();
		
		barrio.comprarTerreno(jugador1);
		barrio.comprarTerreno(jugador2);
		
	}
}
