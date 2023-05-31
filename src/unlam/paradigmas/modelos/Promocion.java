package unlam.paradigmas.modelos;

import java.util.List;

public abstract class Promocion {

	private TipoActividad tipoPaquete;
	private List<Atraccion> atraccionesIncluidas;

	public Promocion(TipoActividad tipoPaquete, List<Atraccion> atracciones) {
		this.tipoPaquete = tipoPaquete;
		this.atraccionesIncluidas = atracciones;
	}

	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public TipoActividad getTipoPaquete() {
		return tipoPaquete;
	}

	public Double getDuracionHorasPromocion() {
		Double horasDuracionTotalPromocion = 0.0;

		for (Atraccion atraccion : this.getAtraccionesIncluidas()) {
			horasDuracionTotalPromocion += atraccion.getDuracionHoras();
		}
		return horasDuracionTotalPromocion;
	}

	public Double getPrecioOriginal() {
		Double precioOriginal = 0.0;
		
		for (Atraccion a : this.getAtraccionesIncluidas()) {
			precioOriginal += a.getCosto();
		}
		return precioOriginal;
	}
}
