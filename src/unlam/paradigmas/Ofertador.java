package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Usuario;

public class Ofertador {

	public Atraccion generarOfertaDeAtraccion(Usuario usuario, List<Atraccion> atracciones) {	
		return atracciones.get(0);
	}

}
