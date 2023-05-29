package unlam.paradigmas.modelos;

//import java.util.Deque;

import java.util.LinkedList;
import java.util.List;

public abstract class Promocion {

	private TipoAtraccion tipoPaquete;
	private List <Atraccion> atraccionesIncluidas;
	
	public Promocion (TipoAtraccion tipoPaquete, String[] atraccionesEnPromo, List<Atraccion> atraccionesVigentes) {
		setTipoPaquete(tipoPaquete);
		atraccionesIncluidas = new LinkedList <Atraccion> ();
		setAtraccionesIncluidas(atraccionesEnPromo, atraccionesVigentes);
	}
	
	private void setAtraccionesIncluidas(String[] atraccionesEnPromo, List <Atraccion> atraccionesVigentes) {
		Atraccion nuevaAtraccion = null;
		for(String s : atraccionesEnPromo) {
			nuevaAtraccion = Atraccion.buscarAtraccionPorNombre(atraccionesVigentes, s);
			this.atraccionesIncluidas.add(nuevaAtraccion);
		}
	}
	
	private void setTipoPaquete(TipoAtraccion tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}

	public List<Atraccion> getAtraccionesIncluidas() {
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
