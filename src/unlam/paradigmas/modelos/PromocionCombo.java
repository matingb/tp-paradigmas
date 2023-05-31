package unlam.paradigmas.modelos;

import java.util.List;

public class PromocionCombo extends Promocion {

	private Integer cantAtraccionesGratis;

	public PromocionCombo(TipoActividad tipoPaquete, Integer cantAtraccionesGratis, List<Atraccion> atracciones ) {
		super(tipoPaquete, atracciones);
		this.cantAtraccionesGratis = cantAtraccionesGratis;
	}

	public Integer getCantAtraccionesGratis() {
		return cantAtraccionesGratis;
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
		return "Promocion combo (" + this.getTipoActividad() + "):" 
				+ "\n-Duracion en Horas del paquete = " + this.getDuracion()
				+ "\n-Precio Original = " + this.getPrecioOriginal()
				+ "\n-Precio Final = " + this.getPrecio() 
				+ "\n-Cantidad de atracciones gratis = " + this.getCantAtraccionesGratis()
				+ "\n-Atracciones Incluidas: " + this.getAtraccionesIncluidas() + "\n";
	}
}
