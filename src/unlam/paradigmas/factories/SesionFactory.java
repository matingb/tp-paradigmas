package unlam.paradigmas.factories;

import java.util.List;

import unlam.paradigmas.Sesion;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;

public class SesionFactory {

	public Sesion create(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		return new Sesion(usuario, atracciones, promociones);
	}
}
