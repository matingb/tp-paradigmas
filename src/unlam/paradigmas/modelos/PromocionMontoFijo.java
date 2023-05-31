package unlam.paradigmas.modelos;

import java.util.List;

public class PromocionMontoFijo extends Promocion {

	private Double precioFinal;

	public PromocionMontoFijo(TipoActividad tipoPaquete, Double precioFinal, List<Atraccion> atracciones) {
		super(tipoPaquete, atracciones);
		this.setPrecioFinal(precioFinal);
	}

	@Override
	public Double getPrecio(){
		return precioFinal;
	}

	private void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}

	@Override
	public String toString() {
		return "Promocion de monto fijo (" + this.getTipoActividad() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracion()
				+ "\n-Precio Original = " + this.getPrecioOriginal() 
				+ "\n-Precio Final = " + this.getPrecio() 
				+ "\n-Atracciones Incluidas: "
				+ this.getAtraccionesIncluidas() + "\n";
	}

}
