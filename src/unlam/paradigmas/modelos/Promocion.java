package unlam.paradigmas.modelos;

import java.util.Deque;

import java.util.LinkedList;
import java.util.List;

public abstract class Promocion {

	private TipoAtraccion tipoPaquete;
	private List <String> atraccionesIncluidas;
	
	public Promocion () {
		atraccionesIncluidas = new LinkedList <String> ();
	}

	public void addAtracciones (String atraccion) {	// Funciona como set de las atracciones incluidas
		this.atraccionesIncluidas.add(atraccion);
	}
	

	public void setTipoPaquete(TipoAtraccion tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}

	public List<String> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public TipoAtraccion getTipoPaquete() {
		return tipoPaquete;
	}

	public void setAtraccionesIncluidas(List<String> atraccionesIncluidas) {
		this.atraccionesIncluidas = atraccionesIncluidas;
	}
	
	
}
