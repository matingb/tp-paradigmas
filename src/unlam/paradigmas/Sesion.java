package unlam.paradigmas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;

public class Sesion {
	
	Usuario usuario;
	List<Oferta> atracciones = new ArrayList<Oferta>();
	List<Oferta> promociones = new ArrayList<Oferta>();
	
	public Sesion(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuario = usuario;
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
		
		Scanner scanner = new Scanner(System.in);
		
		String ingreso;
		
		System.out.println(oferta);
		
		do {
			 System.out.println("¿Acepta sugerencia? Ingrese S o N");
			 ingreso = scanner.nextLine();
			 ingreso.toUpperCase();
		}while(!ingreso.equals("S") && !ingreso.equals("N"));
		
		scanner.close();
		
		return ingreso.equals("S");
	}
	
	public void aceptarOferta(Oferta oferta,Venta venta) {
		usuario.pagarBoleteria(oferta.getPrecio());
		usuario.reducirTiempo(oferta.getDuracion());
		oferta.descontarCupo();
		
		for(Atraccion atraccion : oferta.getAtraccionesIncluidas()) {
			this.atracciones = atracciones.stream().filter(a -> !a.getAtraccionesIncluidas().contains(atraccion)).toList();
			this.promociones = promociones.stream().filter(promocion -> !promocion.getAtraccionesIncluidas().contains(atraccion)).toList();
		}
		
		//CREAR UNA ESPECIE DE VENTA O ALGO PARA ESCRIBIR EL ARCHIVO CON TODO AL TERMINAR
		
		venta.escribirOferta(oferta);
	}
	
	public void rechazarOferta(Oferta oferta) {
		this.atracciones.remove(oferta);	
		this.promociones.remove(oferta);
	}
	
	private Oferta obtenerOferta(List<Oferta> ofertas, Boolean preferidas) {
		if(preferidas) {
			ofertas = ofertas.stream().filter(oferta -> oferta.getTipoActividad().equals(usuario.getActividadFavorita())).toList();			
		} else {
			ofertas = ofertas.stream().filter(oferta -> !oferta.getTipoActividad().equals(usuario.getActividadFavorita())).toList();			
		}
		
		ofertas = ofertas.stream().filter(oferta ->
		oferta.getPrecio() < usuario.getPresupuesto() &&
		oferta.getDuracion() < usuario.getTiempo() &&
		oferta.hayDisponibilidad()).toList();
		
		Collections.sort(ofertas);
		
		return ofertas.size() > 0 ? ofertas.get(0) : null;
	}
}
