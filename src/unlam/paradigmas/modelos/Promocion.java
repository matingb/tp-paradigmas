package unlam.paradigmas.modelos;

//import java.util.Deque;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Promocion {

	private TipoAtraccion tipoPaquete;
	private List <String> atraccionesIncluidas;
	
	public Promocion (TipoAtraccion tipoPaquete, String[] atracciones) {
		setTipoPaquete(tipoPaquete);
		atraccionesIncluidas = new LinkedList <String> ();
		setAtraccionesIncluidas(atracciones);
	}
	
	private void setAtraccionesIncluidas(String[] atracciones) {
		for(String s : atracciones)
			this.addAtracciones(s);
	}
	
	private void setTipoPaquete(TipoAtraccion tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}
	
	private void addAtracciones (String atraccion) {	// Funciona como set de las atracciones incluidas
		this.atraccionesIncluidas.add(atraccion);
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

	@Override
	public int hashCode() {
		return Objects.hash(atraccionesIncluidas, tipoPaquete);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesIncluidas, other.atraccionesIncluidas) && tipoPaquete == other.tipoPaquete;
	}

}
