package unlam.paradigmas.modelos;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private Double porcentajeDescuento;

	public PromocionPorcentual(TipoActividad tipoPaquete, Double porcentajeDescuento, List<Atraccion> atracciones) {
		super(tipoPaquete, atracciones);
		setPorcentajeDescuento(porcentajeDescuento);
	}

	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	private void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public Double getPrecioFinal() {
		return this.getPrecioOriginal()-(this.getPrecioOriginal()*(this.getPorcentajeDescuento()/100));
	}

	@Override
	public String toString() {
		return "Promocion Porcentual (" + this.getTipoPaquete() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracionHorasPromocion()
				+ "\n-Precio Original = " + this.getPrecioOriginal()
				+ "\n-Porcentaje de descuento = " + this.getPorcentajeDescuento() + "%"
				+ "\n-Precio final = " +  this.getPrecioFinal()
				+ "\n-Atracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}

}
