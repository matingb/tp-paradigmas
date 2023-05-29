package unlam.paradigmas.modelos;

import java.util.Objects;

public class PromocionCombo extends Promocion {

	private Integer cantAtraccionesGratis;

	public PromocionCombo(TipoAtraccion tipoPaquete, String[] atracciones, Integer cantAtraccionesGratis) {
		super(tipoPaquete, atracciones);
		this.setCantAtraccionesGratis(cantAtraccionesGratis);
	}

	public Integer getCantAtraccionesGratis() {
		return cantAtraccionesGratis;
	}

	private void setCantAtraccionesGratis(Integer cantAtraccionesGratis) {
		this.cantAtraccionesGratis = cantAtraccionesGratis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cantAtraccionesGratis);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		PromocionCombo other = (PromocionCombo) obj;
		return Objects.equals(cantAtraccionesGratis, other.cantAtraccionesGratis);
	}

}
