package unlam.paradigmas.modelos;

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
	public String toString() {
		return "Promocion combo (" + this.getTipoPaquete() + "):\n" + "Cantidad de atracciones gratis = "
				+ this.getCantAtraccionesGratis();
	}
	
}
