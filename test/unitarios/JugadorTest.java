package unitarios;

import modelo.casilleros.Aysa;
import modelo.casilleros.Carcel;
import modelo.casilleros.Casillero;
import modelo.casilleros.CordobaSur;
import modelo.casilleros.Neuquen;
import modelo.excepciones.ExcepcionCapitalInsuficiente;
import modelo.excepciones.ExcepcionTerrenoOcupado;
import modelo.excepciones.ExcepcionJugadorPreso;
import modelo.excepciones.ExcepcionJugadorYaEstaJugando;
import modelo.excepciones.ExcepcionPagarFianzaNoCorresponde;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import modelo.Jugador;
import modelo.Tablero;
import modelo.casilleros.Edesur;
import modelo.casilleros.Salida;
import modelo.casilleros.Subte;
import modelo.casilleros.Tren;

import org.junit.rules.ExpectedException;

public class JugadorTest {

	private static final double DELTA = 1e-15;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test01JugadorArrojaExcepcionCuandoIntentaPagarMasQueSuCapital() throws ExcepcionCapitalInsuficiente {
		
		Jugador jugador = new Jugador();
		
		thrown.expect(ExcepcionCapitalInsuficiente.class);
		jugador.pagar(200000);
	}
	
	@Test
	public void test02ElJugadorAvanza3CasillerosYCaeEnEdesur() throws ExcepcionJugadorPreso, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
		unTablero.avanzar(unJugador, 3);
		
		Assert.assertEquals(unJugador.getPosicion().getClass(),(new Edesur()).getClass());

	}

	@Test
	public void test03JugadorCompraNeuquenYQuedaComoPropietario() throws ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		Jugador unDuenio = new Jugador();
		Neuquen neuquen = new Neuquen();

		neuquen.caer(unDuenio, 1);

		Assert.assertEquals(neuquen.getPropietario(), unDuenio);
	}

	@Test
	public void test04JugadorCaeEnLaCarcelYNoPuedeDesplazarse() throws ExcepcionJugadorPreso, ExcepcionCapitalInsuficiente {
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		Carcel miCarcel = new Carcel();

		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
		miCarcel.arrestar(unJugador);

		thrown.expect(ExcepcionJugadorPreso.class);
		unTablero.avanzar(unJugador, 1);
	}

	@Test
	public void test05SiUnJugadorAvanza20CasillerosCaeDeNuevoEnLaSalida() throws ExcepcionJugadorPreso, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
		unTablero.avanzar(unJugador, 20);
		
		Assert.assertEquals(unJugador.getPosicion().getClass(),(new Salida()).getClass());
	}
	
	@Test
	public void test06SiUnJugadorAvanza25CasillerosDaLaVueltaYCaeEnCarcel() throws ExcepcionJugadorPreso, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
		
		unTablero.avanzar(unJugador, 25);
		
		Assert.assertEquals((new Carcel()).getClass(), unJugador.getPosicion().getClass());
		
	}
	
	@Test
	public void test07UnJugadorCaeEnAvanceDinamicoDespuesDeHaberSacadoUn3YAvanzaASubte() throws ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente, ExcepcionJugadorPreso {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
		unTablero.avanzar(unJugador, 4); //Dejo al jugador en Bs As Zona sur para avanzar de a 3 a Avance
		unTablero.avanzar(unJugador, 3); //Muevo al jugador desde Bs As Zona sur a avanza dinamico
		
		
		
		Assert.assertEquals((new Subte()).getClass(), unJugador.getPosicion().getClass());
		
		
		
		
	}
	
	@Test
	public void test08UnJugadorCaeEnAvanceDinamicoDespuesDeHaberSacadoUn7YAvanzaAAysa() throws ExcepcionJugadorPreso, ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		Tablero unTablero = new Tablero();
		
		unTablero.agregarJugador(unJugador);
			
		//cae en avance dinamico, rolleando un 7
		unTablero.avanzar(unJugador, 7 );

		
		
		Assert.assertEquals((new Aysa()).getClass(), unJugador.getPosicion().getClass());
		
		
		
		
	}
	
	@Test
	public void test09UnJugadorCaeEnAvanceDinamicoDespuesDeHaberSacadoUn12YCaeEnTren() throws ExcepcionJugadorPreso, ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		
		//Rehacer tests
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void test10UnJugadorCaeEnLaPoliciaYEstaLoLlevaALaCarcel() throws ExcepcionJugadorPreso, ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
			
		//Avanza 15 para caer en la policia
		unTablero.avanzar(unJugador, 15 );
		
				
		Assert.assertEquals((new Carcel()).getClass(), unJugador.getPosicion().getClass());
				
	}
	
	@Test
	public void test11UnJugadorCaeEnLaPoliciaEstaLoLlevaALaCarcelYAhoraNoSePuedeMover() throws ExcepcionJugadorPreso, ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
			
		//Avanza 15 para caer en la policia
		unTablero.avanzar(unJugador, 15 );
		
				
		thrown.expect(ExcepcionJugadorPreso.class);
		unTablero.avanzar(unJugador, 1);
			
	}
	
	@Test
	public void test12UnJugadorCaeEnRetrocesoDinamicoDespuesDeHaberSacadoUn4YRetrocedeATren() throws ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente, ExcepcionJugadorPreso {
		
		Jugador unJugador = new Jugador();
		ArrayList<Jugador> listaJugadores = new ArrayList<>();
		Tablero unTablero = new Tablero();
		
		listaJugadores.add(unJugador);
		unTablero.agregarJugadores(listaJugadores);
		unTablero.avanzar(unJugador, 14); //Dejo al jugador en Salta Sur
		unTablero.avanzar(unJugador, 4); //Muevo al jugador desde Salta Sur a retroceso dinamico
		
		
		
		Assert.assertEquals((new Tren()).getClass(), unJugador.getPosicion().getClass());
		
		
		
		
	}
	
	@Test
	public void test13UnJugadorConCaeEnRetrocesoDinamicoDespuesDeHaberSacadoUn12YRetrocedeAlMismoCasillero() throws ExcepcionJugadorPreso, ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		Tablero unTablero = new Tablero();
		
		unTablero.agregarJugador(unJugador);
			
		unTablero.avanzar(unJugador, 6);
		unTablero.avanzar(unJugador, 12);//Desde CordobaSur saco un 12 y el jugador cae en Retroceso Dinamico

		//Deberia retroceder los 12 casilleros por no tener inmuebles
		
		Assert.assertEquals((new CordobaSur()).getClass(), unJugador.getPosicion().getClass());
		
	
		
	}
	
	@Test
	public void test14UnJugadorCaeEnRetrocesoDinamicoDespuesDeHaberSacadoUn12YCaeEnCordobaSur() throws ExcepcionJugadorPreso, ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente {
		
		Jugador unJugador = new Jugador();
		Tablero unTablero = new Tablero();
		
		unTablero.agregarJugador(unJugador);
		
		//Avanza 6 y cae en Cordoba Sur
		unTablero.avanzar(unJugador, 6 );
		
		// Desde Cordoba sur, saca un 12 y cae en Retroceso Dinamico
		unTablero.avanzar(unJugador, 12 );
		
		//Vuelve a caer en CordobaSur retrocediendo los 12 casilleros por tener 0 inmuebles		
		Assert.assertEquals((new CordobaSur()).getClass(), unJugador.getPosicion().getClass());
				
	}
	
	@Test (expected = ExcepcionPagarFianzaNoCorresponde.class)
	public void test15JugadorPagaFianzaEstandoJugandoArrojaExcepcionPagarFianzaNoCorresponde() throws ExcepcionPagarFianzaNoCorresponde, ExcepcionCapitalInsuficiente {
		
		Jugador jugador = new Jugador();
		
		jugador.pagarFianza();
	}
	
	@Test (expected = ExcepcionJugadorYaEstaJugando.class)
	public void test16JugadorCumplirCondenaArrojaExcepcionJugadorYaEstaJugandoSiElEstadoEsJugando() throws ExcepcionJugadorYaEstaJugando {
		
		Jugador jugador = new Jugador();
		
		jugador.cumplirCondena();
	}
	
	@Test
	public void test17AvanzarJugadorASalidaPoneJugadorEnPosicionSalida() throws ExcepcionJugadorPreso {
		
		Jugador jugador = new Jugador();
		Casillero salida = new Salida();
		
		jugador.avanzar(salida);
		
		Assert.assertEquals(salida, jugador.getPosicion());
	}
	
	@Test (expected = ExcepcionJugadorPreso.class)
	public void test18AvanzarJugadorASalidaArrojaExcepcionJugadorPresoCuandoElEstadoEsPreso() throws ExcepcionJugadorPreso {
		
		Jugador jugador = new Jugador();
		Casillero salida = new Salida();
		Carcel carcel = new Carcel();
		
		jugador.irPreso(carcel);
		jugador.avanzar(salida);
	}
	
	@Test
	public void test19JugadorPaga50000YLeQuedaLaMitadDeSuCapital() throws ExcepcionCapitalInsuficiente {
		
		Jugador jugador = new Jugador();
		
		jugador.pagar(50000);
		
		Assert.assertEquals(100000 - 50000, jugador.getCapital(), DELTA);
	}
	
	@Test
	public void test20JugadorCobra100000YLeQuedaElDobleDeSuCapital() {
		
		Jugador jugador = new Jugador();
		
		jugador.acreditar(100000);
		
		Assert.assertEquals(100000 + 100000, jugador.getCapital(), DELTA);
	}
}
