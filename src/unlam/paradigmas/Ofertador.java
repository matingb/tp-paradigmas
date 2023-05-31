package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.Usuario;

public class Ofertador {

	public Atraccion generarOfertaDeAtraccion(Usuario usuario, List<Atraccion> atracciones) {	
		atracciones = atracciones.stream().filter(atraccion ->
		atraccion.getCosto() < usuario.getPresupuesto() &&
		atraccion.getDuracionHoras() < usuario.getTiempo() && 
		atraccion.getCupo() > 0).toList();
		
		return atracciones != null	? atracciones.get(0) : null;
	}
	
	public Promocion generarPromocion(List<Promocion> promociones) {
		return promociones != null ? promociones.get(0) : null;
	}

}
