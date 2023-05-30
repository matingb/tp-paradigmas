package unlam.paradigmas.modelos;

import java.util.LinkedList;
import java.util.List;

public abstract class Promocion {

	private TipoAtraccion tipoPaquete;
	private List<Atraccion> atraccionesIncluidas;

	public Promocion(TipoAtraccion tipoPaquete, List<Atraccion> atracciones) {
		this.tipoPaquete = tipoPaquete;
		this.atraccionesIncluidas = atracciones;
	}

	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public TipoAtraccion getTipoPaquete() {
		return tipoPaquete;
	}

	public Double getDuracionHorasPromocion() {
		Double horasDuracionTotalPromocion = 0.0;

		for (Atraccion a : this.getAtraccionesIncluidas()) {
			horasDuracionTotalPromocion += a.getDuracionHoras();
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
