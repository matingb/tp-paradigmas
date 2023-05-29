package unlam.paradigmas.modelos;

public class PromocionMontoFijo extends Promocion{
	
	private Double precioFinal;
	
	public PromocionMontoFijo (TipoAtraccion tipoPaquete, String[] atracciones, Double precioFinal) {
		super(tipoPaquete, atracciones);
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
		System.out.print("HOLAAAA");
		return "PromocionMontoFijo [Tipo Paquete=" + this.getTipoPaquete() + "Precio Final=" + precioFinal + "]";
	}
	
	
}
