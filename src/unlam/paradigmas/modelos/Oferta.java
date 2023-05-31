package unlam.paradigmas.modelos;

import java.util.List;

public abstract class Oferta {

	public abstract Double getPrecio();
	public abstract Double getDuracion();
	public abstract void descontarCupo();
	public abstract TipoActividad getTipoActividad();
	public abstract Boolean hayDisponibilidad();
	public abstract List<Atraccion> getAtraccionesIncluidas();
}
