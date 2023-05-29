package unlam.paradigmas.modelos;

import java.util.Objects;

public class PromocionPorcentual extends Promocion{
	
	private Double procentajeDescuento;
	
	public PromocionPorcentual (TipoAtraccion tipoPaquete, String[] atracciones, Double porcentajeDescuento) {
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(procentajeDescuento);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if( !super.equals(obj)) {
			return false;
		}
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Objects.equals(procentajeDescuento, other.procentajeDescuento);
	}
	
	
}
