package unlam.paradigmas.modelos;

import java.util.Objects;

public class PromocionPorcentual extends Promocion {

	private Double procentajeDescuento;

	public PromocionPorcentual(TipoAtraccion tipoPaquete, String[] atracciones, Double porcentajeDescuento) {
		super(tipoPaquete, atracciones);
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
				+ this.getProcentajeDescuento() + "%\n";
	}

}
