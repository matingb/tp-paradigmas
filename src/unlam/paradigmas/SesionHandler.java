package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.Usuario;

public class SesionHandler {

	public Sesion generarSesion(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		return new Sesion(usuario, atracciones, promociones);
	}
}
