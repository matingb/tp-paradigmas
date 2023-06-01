package unlam.paradigmas.modelos.ofertas.promociones;

import java.util.List;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class PromocionPorcentual extends Promocion {

	private Double porcentajeDescuento;

	public PromocionPorcentual(TipoActividad tipoActividad, Double porcentajeDescuento, List<Atraccion> atracciones) {
		super(tipoActividad, atracciones);
		setPorcentajeDescuento(porcentajeDescuento);
	}

	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	private void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	@Override
	public Double getPrecio() {
		return this.getPrecioOriginal()-(this.getPrecioOriginal()*(this.getPorcentajeDescuento()/100));
	}

	@Override
	public String toString() {
		return "Promocion Porcentual (" + this.getTipoActividad() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracion()
				+ "\n-Precio Original = " + this.getPrecioOriginal()
				+ "\n-Porcentaje de descuento = " + this.getPorcentajeDescuento() + "%"
				+ "\n-Precio final = " +  this.getPrecio()
				+ "\n-Atracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}

}
