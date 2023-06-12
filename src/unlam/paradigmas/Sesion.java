package unlam.paradigmas;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import unlam.paradigmas.modelos.Recibo;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;

public class Sesion {
	
	Usuario usuario;
	List<Oferta> atracciones = new ArrayList<Oferta>();
	List<Oferta> promociones = new ArrayList<Oferta>();
	Recibo recibo;
	Lector lector = Lector.getInstance();
	
	public Sesion(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuario = usuario;
		this.recibo = new Recibo(usuario);
		this.atracciones.addAll(atracciones);
		this.promociones.addAll(promociones);
	}
	
	public Oferta generarOferta() {
		
		Oferta oferta = obtenerOferta(this.promociones, true);
		
		if(oferta == null) {
			oferta = obtenerOferta(this.atracciones, true);
			
			if(oferta == null) {
				oferta = obtenerOferta(this.promociones, false);
				
				if(oferta == null) {
					oferta = obtenerOferta(this.atracciones, false);
				}
			}
		}

		return oferta;
	}
	
	public Boolean sugerir(Oferta oferta) {
		String ingreso;
		
		DecimalFormat formato = new DecimalFormat("#0.00");
		System.out.println("\nPresupuesto disponible: " + formato.format(this.usuario.getPresupuesto()));
		System.out.println("Tiempo disponible: " + this.usuario.getTiempo());
		System.out.println();
		System.out.println(oferta);
		System.out.println("¿Acepta sugerencia? Ingrese S o N");
		ingreso = lector.leer().toUpperCase();
		
		while(!ingreso.equals("S") && !ingreso.equals("N")) {
			 System.out.println("Valor invalido. Ingrese S o N");
			 ingreso = lector.leer().toUpperCase();
		}

		return ingreso.equals("S");
	}
	
	public void aceptarOferta(Oferta oferta) {
		System.out.println("¡Aceptada!");
		usuario.pagarBoleteria(oferta.getPrecio());
		usuario.reducirTiempo(oferta.getDuracion());
		oferta.descontarCupo();
		
		for(Atraccion atraccion : oferta.getAtraccionesIncluidas()) {
			this.atracciones = atracciones.stream().filter(a -> !a.getAtraccionesIncluidas().contains(atraccion)).collect(Collectors.toList());
			this.promociones = promociones.stream().filter(promocion -> !promocion.getAtraccionesIncluidas().contains(atraccion)).collect(Collectors.toList());
		}
		
		recibo.agregarVenta(oferta);
	}
	
	public void rechazarOferta(Oferta oferta) {
		System.out.println("¡Rechazada!");
		this.atracciones.remove(oferta);	
		this.promociones.remove(oferta);
	}
	
	public Recibo getRecibo() {
		return this.recibo;
	}
	
	private Oferta obtenerOferta(List<Oferta> ofertas, Boolean preferidas) {

		ofertas = preferidas ? filtrarPorOfertasPreferidas(ofertas) : filtrarPorOfertasNoPreferidas(ofertas);
	
		ofertas = ofertas.stream().filter(oferta ->
		oferta.getPrecio() <= usuario.getPresupuesto() &&
		oferta.getDuracion() <= usuario.getTiempo() &&
		oferta.hayDisponibilidad()).collect(Collectors.toList());
		
		Collections.sort(ofertas);
		
		return ofertas.size() > 0 ? ofertas.get(0) : null;
	}

	private List<Oferta> filtrarPorOfertasNoPreferidas(List<Oferta> ofertas) {
		return ofertas.stream().filter(oferta -> !oferta.getTipoActividad().equals(usuario.getActividadFavorita())).collect(Collectors.toList());
	}

	private List<Oferta> filtrarPorOfertasPreferidas(List<Oferta> ofertas) {
		return ofertas.stream().filter(oferta -> oferta.getTipoActividad().equals(usuario.getActividadFavorita())).collect(Collectors.toList());
	}
}
