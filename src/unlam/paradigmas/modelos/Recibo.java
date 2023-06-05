package unlam.paradigmas.modelos;

import java.util.ArrayList;
import java.util.List;

import unlam.paradigmas.modelos.ofertas.Oferta;

public class Recibo {
	private double precioTotal;
	private double tiempoTotal;
	private Usuario usuario;
	private List<Oferta> ofertasVendidas = new ArrayList<Oferta>();

	public Recibo(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void agregarVenta(Oferta ofertaVendida) {
		this.ofertasVendidas.add(ofertaVendida);
		this.precioTotal += ofertaVendida.getPrecio();
		this.tiempoTotal += ofertaVendida.getDuracion();
	}

	public double getTiempoTotal() {
		return tiempoTotal;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Oferta> getOfertasVendidas() {
		return ofertasVendidas;
	}

	public boolean hayVentas() {
		return this.ofertasVendidas.size() > 0;
	}
}
