package modelo.EstadosJugador;

import modelo.Jugador;
import modelo.Tablero;
import modelo.excepciones.ExcepcionCapitalInsuficiente;
import modelo.excepciones.JugadorEstaPresoException;

/**
 * Created by nico on 12/11/17.
 */
public class PresoBajoFianza1 implements EstadoJugador {

	private final double multa = 45000;

	@Override
	public boolean esLibre() {
		return false;
	}
	
    @Override
    public int avanzar(int cantidadDePasos) throws JugadorEstaPresoException {
        throw new JugadorEstaPresoException();
    }

    @Override
    public EstadoJugador siguienteEstado() {
        return new PresoBajoFianza2();
    }
    
    @Override
    public void pagarFianza(Jugador jugador) throws ExcepcionCapitalInsuficiente {
    	jugador.pagar(multa);
    	jugador.cambiarEstado(new Jugando());
    }
}
