package unlam.paradigmas.services;

import java.util.List;

import unlam.paradigmas.Sesion;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;

public class SesionService {

	public Sesion abrir(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		return new Sesion(usuario, atracciones, promociones);
	}
}
