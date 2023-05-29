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
	
	public Double getPrecioFinal() {
		Double precio = this.getPrecioOriginal();
		for(int i = 0; i < this.getCantAtraccionesGratis(); i++) {
			precio -= this.getAtraccionesIncluidas().get(i).getCosto();
		}
		return precio;
	}
	
	@Override
	public String toString() {
		return "Promocion combo (" + this.getTipoPaquete() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracionHorasPromocion()
				+ "\n-Precio Original = " + this.getPrecioOriginal()
				+ "\n-Precio Final = " + this.getPrecioFinal() 
				+ "\n-Cantidad de atracciones gratis = " + this.getCantAtraccionesGratis()
				+ "\n-Atracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}
	}