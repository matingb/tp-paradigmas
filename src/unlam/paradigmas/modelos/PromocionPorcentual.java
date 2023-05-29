package unlam.paradigmas.modelos;

import java.util.List;
//import java.util.Objects;

public class PromocionPorcentual extends Promocion {

	private Double procentajeDescuento;

	public PromocionPorcentual(TipoAtraccion tipoPaquete, Double porcentajeDescuento, String[] atraccionesEnPromo,
			List<Atraccion> atraccionesVigentes) {
		super(tipoPaquete, atraccionesEnPromo, atraccionesVigentes);
		setProcentajeDescuento(porcentajeDescuento);
	}

	public Double getProcentajeDescuento() {
		return procentajeDescuento;
	}

	private void setProcentajeDescuento(Double procentajeDescuento) {
		this.procentajeDescuento = procentajeDescuento;
	}
	
	public Double getPrecioFinal() {
		return this.getPrecioOriginal()-(this.getPrecioOriginal()*(this.getProcentajeDescuento()/100));
	}

	@Override
	public String toString() {
		return "Promocion Porcentual (" + this.getTipoPaquete() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracionHorasPromocion()
				+ "\n-Precio Original = " + this.getPrecioOriginal()
				+ "\n-Porcentaje de descuento = " + this.getProcentajeDescuento() + "%"
				+ "\n-Precio final = " +  this.getPrecioFinal()
				+ "\n-Atracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}

}
