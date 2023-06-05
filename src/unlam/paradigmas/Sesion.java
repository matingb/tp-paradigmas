package unlam.paradigmas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
	
	public Boolean sugerir(Oferta oferta, Scanner scanner) {
		String ingreso;
		
		System.out.println("Presupuesto disponible: " + this.usuario.getPresupuesto());
		System.out.println("Tiempo disponible: " + this.usuario.getTiempo());
		System.out.println();
		System.out.println(oferta);
		System.out.println("¿Acepta sugerencia? Ingrese S o N");
		ingreso = scanner.nextLine().toUpperCase();
		
		while(!ingreso.equals("S") && !ingreso.equals("N")) {
			 System.out.println("Valor invalido. Ingrese S o N");
			 ingreso = scanner.nextLine().toUpperCase();
		}

		return ingreso.equals("S");
	}
	
	public void aceptarOferta(Oferta oferta) {
		usuario.pagarBoleteria(oferta.getPrecio());
		usuario.reducirTiempo(oferta.getDuracion());
		oferta.descontarCupo();
		
		for(Atraccion atraccion : oferta.getAtraccionesIncluidas()) {
			this.atracciones = atracciones.stream().filter(a -> !a.getAtraccionesIncluidas().contains(atraccion)).collect(Collectors.toCollection(ArrayList::new));
			this.promociones = promociones.stream().filter(promocion -> !promocion.getAtraccionesIncluidas().contains(atraccion)).collect(Collectors.toCollection(ArrayList::new));
		}
		
		recibo.agregarVenta(oferta);
	}
	
	public void rechazarOferta(Oferta oferta) {
		this.atracciones.remove(oferta);	
		this.promociones.remove(oferta);
	}
	
	public Recibo getRecibo() {
		return this.recibo;
	}
	
	private Oferta obtenerOferta(List<Oferta> ofertas, Boolean preferidas) {
		
		if(preferidas) {
			ofertas = ofertas.stream().filter(oferta -> oferta.getTipoActividad().equals(usuario.getActividadFavorita())).collect(Collectors.toCollection(ArrayList::new));	
		} else {
			ofertas = ofertas.stream().filter(oferta -> !oferta.getTipoActividad().equals(usuario.getActividadFavorita())).collect(Collectors.toCollection(ArrayList::new));			
		}
		
		ofertas = ofertas.stream().filter(oferta ->
		oferta.getPrecio() <= usuario.getPresupuesto() &&
		oferta.getDuracion() <= usuario.getTiempo() &&
		oferta.hayDisponibilidad()).toList();
		
		List <Oferta> ofertasFiltrada = new ArrayList <Oferta>();
		for(Oferta o: ofertas) {
			ofertasFiltrada.add(o);
		}
		
		Collections.sort(ofertasFiltrada);
		//Oferta ofertaMayorCostoDuracion = Collections.max(ofertas, Comparator.comparingDouble(Oferta::getPrecio).thenComparingDouble(Oferta::getDuracion));
		
		return ofertas.size() > 0 ? ofertasFiltrada.get(0) : null;
	}
}
