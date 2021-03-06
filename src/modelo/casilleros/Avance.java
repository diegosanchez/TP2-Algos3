package modelo.casilleros;

import modelo.Jugador;
import modelo.Tablero;
import modelo.excepciones.ExcepcionCapitalInsuficiente;
import modelo.excepciones.ExcepcionJugadorPreso;

public class Avance implements Casillero {
	
	Tablero tablero;
	
	public Avance(Tablero referencia) {
		
		tablero = referencia;
	}

	private void avanzarDinamicamente(Jugador jugador, int valorDados) throws ExcepcionJugadorPreso, ExcepcionCapitalInsuficiente {
		
		if (valorDados <= 6) {
			
			tablero.avanzar(jugador, valorDados-2);
		
		} else if (valorDados > 6 && valorDados <= 10) {
			
			tablero.avanzar(jugador, (int) (jugador.getCapital() % valorDados));
			
		} else {
			
			tablero.avanzar(jugador, Math.abs(valorDados - jugador.cantidadDeInmuebles()));
			
		}	
	}

	@Override
	public void caer(Jugador jugador, int valorDados)
			throws ExcepcionJugadorPreso, ExcepcionCapitalInsuficiente {
		
		this.avanzarDinamicamente(jugador, valorDados);
	}

	
}
