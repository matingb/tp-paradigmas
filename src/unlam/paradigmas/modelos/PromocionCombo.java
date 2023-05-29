package unlam.paradigmas.modelos;

import java.util.List;

public class PromocionCombo extends Promocion {

	private Integer cantAtraccionesGratis;

	public PromocionCombo(TipoAtraccion tipoPaquete, Integer cantAtraccionesGratis, String[] atraccionesEnPromo, List<Atraccion> atraccionesVigentes ) {
		super(tipoPaquete, atraccionesEnPromo, atraccionesVigentes);
		this.setCantAtraccionesGratis(cantAtraccionesGratis);
	}

	public Integer getCantAtraccionesGratis() {
		return cantAtraccionesGratis;
	}

	private void setCantAtraccionesGratis(Integer cantAtraccionesGratis) {
		this.cantAtraccionesGratis = cantAtraccionesGratis;
	}
	
	@Override
	public String toString() {
		return "Promocion combo (" + this.getTipoPaquete() + "):\n" + "Cantidad de atracciones gratis = "
				+ this.getCantAtraccionesGratis() + "\nAtracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}
	}
