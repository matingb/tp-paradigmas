package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;

public class Ofertador {

	public Atraccion generarOfertaDeAtraccion(List<Atraccion> atracciones) {	
		return atracciones != null	? atracciones.get(0) : null;
	}
	
	public Promocion generarPromocion(List<Promocion> promociones) {
		return promociones != null ? promociones.get(0) : null;
	}

}
