package unlam.paradigmas.modelos;

import java.util.List;

public class PromocionMontoFijo extends Promocion {

	private Double precioFinal;

	public PromocionMontoFijo(TipoAtraccion tipoPaquete, Double precioFinal, String[] atraccionesEnPromo,
			List<Atraccion> atraccionesVigentes) {
		super(tipoPaquete, atraccionesEnPromo, atraccionesVigentes);
		this.setPrecioFinal(precioFinal);
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}

	private void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}

	@Override
	public String toString() {
		return "Promocion de monto fijo (" + this.getTipoPaquete() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracionHorasPromocion()
				+ "\n-Precio Original = " + this.getPrecioOriginal() 
				+ "\n-Precio Final = " + this.getPrecioFinal() 
				+ "\n-Atracciones Incluidas: "
				+ this.getAtraccionesIncluidas() + "\n";
	}

}
