package unlam.paradigmas.modelos.ofertas;

import java.util.List;

import unlam.paradigmas.enums.TipoActividad;

public abstract class Oferta {
	
	private TipoActividad tipoActividad;
	
	public Oferta(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public abstract Double getPrecio();
	public abstract Double getDuracion();
	public abstract void descontarCupo();
	public abstract Boolean hayDisponibilidad();
	public abstract List<Atraccion> getAtraccionesIncluidas();
	
	public TipoActividad getTipoActividad() {
		return this.tipoActividad;
	}
}
