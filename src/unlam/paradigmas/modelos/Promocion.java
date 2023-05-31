package unlam.paradigmas.modelos;

import java.util.List;

public abstract class Promocion extends Oferta {

	private TipoActividad tipoActividad;
	private List<Atraccion> atraccionesIncluidas;

	public Promocion(TipoActividad tipoPaquete, List<Atraccion> atracciones) {
		this.tipoActividad = tipoPaquete;
		this.atraccionesIncluidas = atracciones;
	}

	@Override
	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	@Override
	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	@Override
	public Double getDuracion() {
		Double horasDuracionTotalPromocion = 0.0;

		for (Atraccion atraccion : this.getAtraccionesIncluidas()) {
			horasDuracionTotalPromocion += atraccion.getDuracion();
		}
		return horasDuracionTotalPromocion;
	}
	
	@Override
	public void descontarCupo() {
		for(Atraccion atraccion : atraccionesIncluidas) {
			atraccion.descontarCupo();
		}
	}
	
	@Override
	public Boolean hayDisponibilidad() {
		Boolean hayDisponibilidad = true;
		for(Atraccion atraccion : atraccionesIncluidas) {
			if(atraccion.getCupo() <= 0) {
				hayDisponibilidad = false;
			}
		}
		return hayDisponibilidad;
	}

	public Double getPrecioOriginal() {
		Double precioOriginal = 0.0;
		
		for (Atraccion a : this.getAtraccionesIncluidas()) {
			precioOriginal += a.getPrecio();
		}
		return precioOriginal;
	}
}
