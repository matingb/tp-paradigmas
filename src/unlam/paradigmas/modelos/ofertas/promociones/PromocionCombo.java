package unlam.paradigmas.modelos.ofertas.promociones;

import java.util.List;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class PromocionCombo extends Promocion {

	private Integer cantAtraccionesGratis;

	public PromocionCombo(TipoActividad tipoPaquete, Integer cantAtraccionesGratis, List<Atraccion> atracciones ) {
		super(tipoPaquete, atracciones);
		this.cantAtraccionesGratis = cantAtraccionesGratis;
	}

	public Integer getCantAtraccionesGratis() {
		return cantAtraccionesGratis;
	}
	
	private String nombreAtraccionesGratis (List<Atraccion> atraccionesIncluidas) {
		String atraccionesGratis = atraccionesIncluidas.get(0).getNombre();
		for (int i = 1; i < this.getCantAtraccionesGratis(); i++) {
			atraccionesGratis += ", " + atraccionesIncluidas.get(i).getNombre();
		}
		return atraccionesGratis;
	}
	
	@Override
	public Double getPrecio() {
		Double precio = this.getPrecioOriginal();
		for(int i = 0; i < this.getCantAtraccionesGratis(); i++) {
			precio -= this.getAtraccionesIncluidas().get(i).getPrecio();
		}
		return precio;
	}
	
	@Override
	public String toString() {
		//TODO Arreglar esta salida, se ve feo el output y estaría bueno especificar cual es la gratis, 
		return "Promocion combo (" + this.getTipoActividad() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracion()
				+ "\n-Precio Original = " + this.getPrecioOriginal()
				+ "\n-Precio Final = " + this.getPrecio() 
				+ "\n-Cantidad de atracciones gratis = " + this.getCantAtraccionesGratis()
				+ "\n-Atracciones gratis = " + this.nombreAtraccionesGratis(getAtraccionesIncluidas())
				+ "\n-Atracciones Incluidas: \n" 
				+ this.mostrarAtraccionesIncluidas() + "\n";
	}
}
