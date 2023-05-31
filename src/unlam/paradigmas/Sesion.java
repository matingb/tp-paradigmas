package unlam.paradigmas;

import java.util.List;
import java.util.ArrayList;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Oferta;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.Usuario;

public class Sesion {
	
	Usuario usuario;
	List<Atraccion> atracciones;
	List<Promocion> promociones;
	
	public Sesion(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuario = usuario;
		this.atracciones = atracciones;
		this.promociones = promociones;
	}
	
	public Oferta generarOferta() {
		
		Oferta oferta = obtenerOfertaParaPromocionesPreferidas();
		
		if(oferta == null) {
			oferta = obtenerOfertaParaAtraccionesPreferidas();
			
			if(oferta == null) {
				oferta = obtenerOfertaParaPromocionesNoPreferidas();
				
				if(oferta == null) {
					oferta = obtenerOfertaParaAtraccionesNoPreferidas();
				}
			}
		}
		
		return oferta;
	}
	
	public Boolean sugerir(Oferta oferta) {
		System.out.println(oferta);
		return true;
	}
	
	private Oferta obtenerOfertaParaPromocionesPreferidas() {
		List<Oferta> ofertasPosibles = new ArrayList<Oferta>(); 
		ofertasPosibles.addAll(this.promociones);
		ofertasPosibles = ofertasPosibles.stream().filter(oferta -> oferta.getTipoActividad().equals(usuario.getActividadFavorita())).toList();
		
		return obtenerMejorOferta(ofertasPosibles);
	}
	
	private Oferta obtenerOfertaParaAtraccionesPreferidas() {
		List<Oferta> ofertasPosibles = new ArrayList<Oferta>(); 
		ofertasPosibles.addAll(this.atracciones);
		ofertasPosibles = ofertasPosibles.stream().filter(oferta -> oferta.getTipoActividad().equals(usuario.getActividadFavorita())).toList();
		
		return obtenerMejorOferta(ofertasPosibles);
	}
	
	private Oferta obtenerOfertaParaPromocionesNoPreferidas() {
		List<Oferta> ofertasPosibles = new ArrayList<Oferta>(); 
		ofertasPosibles.addAll(this.promociones);
		ofertasPosibles = ofertasPosibles.stream().filter(oferta -> !oferta.getTipoActividad().equals(usuario.getActividadFavorita())).toList();
		
		return obtenerMejorOferta(ofertasPosibles);
	}
	
	private Oferta obtenerOfertaParaAtraccionesNoPreferidas() {
		List<Oferta> ofertasPosibles = new ArrayList<Oferta>(); 
		ofertasPosibles.addAll(this.atracciones);
		ofertasPosibles = ofertasPosibles.stream().filter(oferta -> !oferta.getTipoActividad().equals(usuario.getActividadFavorita())).toList();
		
		return obtenerMejorOferta(ofertasPosibles);
	}
	
	private Oferta obtenerMejorOferta(List<Oferta> ofertas) {
		ofertas = ofertas.stream().filter(oferta ->
		oferta.getPrecio() < usuario.getPresupuesto() &&
		oferta.getDuracion() < usuario.getTiempo() &&
		oferta.hayDisponibilidad()).toList();
		
		return ofertas.get(0);
	}

	public void aceptarOferta(Oferta oferta) {
		usuario.pagarBoleteria(oferta.getPrecio());
		usuario.reducirTiempo(oferta.getDuracion());
		oferta.descontarCupo();
		
		for(Atraccion atraccion : oferta.getAtraccionesIncluidas()) {
			this.atracciones = atracciones.stream().filter(a -> atraccion.getNombre() != a.getNombre()).toList();
			this.promociones = promociones.stream().filter(promocion -> !promocion.getAtraccionesIncluidas().contains(atraccion)).toList();
		}
		
	}
}
