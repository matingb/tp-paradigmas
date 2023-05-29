package unlam.paradigmas.modelos;

public class PromocionPorcentual extends Promocion{
	
	private Double procentajeDescuento;
	
	public PromocionPorcentual () {
		super();
	}

	public Double getProcentajeDescuento() {
		return procentajeDescuento;
	}

	public void setProcentajeDescuento(Double procentajeDescuento) {
		this.procentajeDescuento = procentajeDescuento;
	}
	
}
