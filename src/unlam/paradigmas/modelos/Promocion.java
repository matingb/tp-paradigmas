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

	//TODO:
	//public Double getDuracionPromocion()
	//public Double getPrecioOriginal()
	//PROPIO DE CADA SUBCLASE: public Double getPrecioConDescuento()
}
