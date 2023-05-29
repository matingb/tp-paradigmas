package unlam.paradigmas.modelos;

import java.util.List;
//import java.util.Objects;

public class PromocionPorcentual extends Promocion {

	private Double procentajeDescuento;

	public PromocionPorcentual(TipoAtraccion tipoPaquete, Double porcentajeDescuento, String[] atraccionesEnPromo, List<Atraccion> atraccionesVigentes) {
		super(tipoPaquete, atraccionesEnPromo, atraccionesVigentes);
		setProcentajeDescuento(porcentajeDescuento);
	}

	public Double getProcentajeDescuento() {
		return procentajeDescuento;
	}

	private void setProcentajeDescuento(Double procentajeDescuento) {
		this.procentajeDescuento = procentajeDescuento;
	}

	@Override
	public String toString() {
		return "Promocion Porcentual (" + this.getTipoPaquete() + "):\n" + "Porcentaje de descuento = "
				+ this.getProcentajeDescuento() + "%" + "\nAtracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}

}
