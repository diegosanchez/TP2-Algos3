package modelo.casilleros.estados;

import modelo.Jugador;
import modelo.excepciones.ExcepcionCapitalInsuficiente;
import modelo.excepciones.ExcepcionTerrenoOcupado;

public interface Estado {
	
	public void comprarTerreno(Jugador jugador, double precioTerreno) throws ExcepcionTerrenoOcupado, ExcepcionCapitalInsuficiente;
}
