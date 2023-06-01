package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;

public class SesionHandler {

	public Sesion generarSesion(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		return new Sesion(usuario, atracciones, promociones);
	}
}
