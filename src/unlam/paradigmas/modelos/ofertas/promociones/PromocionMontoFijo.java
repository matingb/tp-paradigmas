package unlam.paradigmas.modelos.ofertas.promociones;

import java.util.List;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class PromocionMontoFijo extends Promocion {

	private Double precioFinal;

	public PromocionMontoFijo(TipoActividad tipoPaquete, Double precioFinal, List<Atraccion> atracciones) {
		super(tipoPaquete, atracciones);
		this.precioFinal = precioFinal;
	}

	@Override
	public Double getPrecio(){
		return precioFinal;
	}

	@Override
	public String toString() {
		return "Promocion de monto fijo (" + this.getTipoActividad() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracion()
				+ "\n-Precio Original = " + this.getPrecioOriginal() 
				+ "\n-Precio Final = " + this.getPrecio() 
				+ "\n-Atracciones Incluidas: \n" 
				+ this.mostrarAtraccionesIncluidas() + "\n";
	}

}
