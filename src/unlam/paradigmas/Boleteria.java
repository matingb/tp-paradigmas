package unlam.paradigmas;

import java.util.List;
import java.util.ArrayList;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.IAtraccionRepository;
import unlam.paradigmas.repositorios.IPromocionRepository;

public class Boleteria {
	
	List<Atraccion> atracciones;
	List<Promocion> promociones;
	Sugeridor sugeridor;
	Ofertador ofertador;
	
	public Boleteria(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, Ofertador ofertador, Sugeridor sugeridor) {
		this.atracciones = atraccionRepository.getAtracciones();
		this.promociones = promocionRepository.getPromociones();
		this.ofertador = ofertador;
		this.sugeridor = sugeridor;
	}
	
	public void atender(Usuario usuario) {
		List<Promocion> promocionesPosibles = promociones.stream().filter(promocion ->
		promocion.getTipoPaquete() == usuario.getActividadFavorita() &&
		promocion.getPrecioOriginal() < usuario.getPresupuesto() && 
		promocion.getDuracionHorasPromocion() < usuario.getTiempo()).toList();
		
		while(!promocionesPosibles.isEmpty()) {
			Promocion oferta = ofertador.generarPromocion(promocionesPosibles);
			if(sugeridor.sugerir(oferta)) {
				usuario.pagarBoleteria(oferta.getPrecioOriginal());
				usuario.reducirTiempo(oferta.getDuracionHorasPromocion());
				
				// Falta agregar que reduzca el cupo de las atracciones incluidas en la promocion
			}
			
			promocionesPosibles.stream().filter(promocion ->
			promocion.getAtraccionesIncluidas() != oferta.getAtraccionesIncluidas() && // Como no tiene nombre la promocion la distingui por las atracciones que incluye
			promocion.getPrecioOriginal() < usuario.getPresupuesto() && 
			promocion.getDuracionHorasPromocion() < usuario.getTiempo()).toList();
			
			// Hay que agregar el filtro del cupo de las atracciones que estan incluidas en la promocion
		}
		
		
		List<Atraccion> atraccionesPosibles = atracciones.stream().filter(atraccion -> 
			atraccion.getTipoActividad() == usuario.getActividadFavorita()).toList();

		atraccionesPosibles = atraccionesPosibles.stream().filter(atraccion ->
		atraccion.getCosto() < usuario.getPresupuesto() &&
		atraccion.getDuracionHoras() < usuario.getTiempo() && 
		atraccion.getCupo() > 0).toList();
		
		
		while (!atraccionesPosibles.isEmpty()) {

			Atraccion oferta = ofertador.generarOfertaDeAtraccion(atraccionesPosibles);
			if(sugeridor.sugerir(oferta)) {
				usuario.pagarBoleteria(oferta.getCosto());
				usuario.reducirTiempo(oferta.getDuracionHoras());
				oferta.reducirCupo();
			}
			
			atraccionesPosibles.stream().filter(atraccion -> 
			atraccion.getNombre() != oferta.getNombre()).toList();	
			
			atraccionesPosibles = atraccionesPosibles.stream().filter(atraccion ->
			atraccion.getCosto() < usuario.getPresupuesto() &&
			atraccion.getDuracionHoras() < usuario.getTiempo() && 
			atraccion.getCupo() > 0).toList();
			
		}
		
		List<Promocion> promocionesNoPreferidas = promociones.stream().filter(promocion ->
		promocion.getTipoPaquete() != usuario.getActividadFavorita() &&
		promocion.getPrecioOriginal() < usuario.getPresupuesto() && 
		promocion.getDuracionHorasPromocion() < usuario.getTiempo()).toList();
		
		while(!promocionesNoPreferidas.isEmpty()) {
			Promocion oferta = ofertador.generarPromocion(promocionesNoPreferidas);
			if(sugeridor.sugerir(oferta)) {
				usuario.pagarBoleteria(oferta.getPrecioOriginal());
				usuario.reducirTiempo(oferta.getDuracionHorasPromocion());
				
				// Falta agregar que reduzca el cupo de las atracciones incluidas en la promocion
			}
			
			promocionesNoPreferidas.stream().filter(promocion ->
			promocion.getAtraccionesIncluidas() != oferta.getAtraccionesIncluidas() && // Como no tiene nombre la promocion la distingui por las atracciones que incluye
			promocion.getPrecioOriginal() < usuario.getPresupuesto() && 
			promocion.getDuracionHorasPromocion() < usuario.getTiempo()).toList();
			
			// Hay que agregar el filtro del cupo de las atracciones que estan incluidas en la promocion
		}
		
		
		List<Atraccion> atraccionesNoPreferidas = atracciones.stream().filter(atraccion -> 
		atraccion.getTipoActividad() != usuario.getActividadFavorita()).toList();

		atraccionesNoPreferidas = atraccionesNoPreferidas.stream().filter(atraccion ->
		atraccion.getCosto() < usuario.getPresupuesto() &&
		atraccion.getDuracionHoras() < usuario.getTiempo() && 
		atraccion.getCupo() > 0).toList();
	
	
		while (!atraccionesNoPreferidas.isEmpty()) {

			Atraccion oferta = ofertador.generarOfertaDeAtraccion(atraccionesNoPreferidas);
			if(sugeridor.sugerir(oferta)) {
				usuario.pagarBoleteria(oferta.getCosto());
				usuario.reducirTiempo(oferta.getDuracionHoras());
				oferta.reducirCupo();
			}
		
			atraccionesNoPreferidas.stream().filter(atraccion -> 
			atraccion.getNombre() != oferta.getNombre()).toList();	
		
			atraccionesNoPreferidas = atraccionesNoPreferidas.stream().filter(atraccion ->
			atraccion.getCosto() < usuario.getPresupuesto() &&
			atraccion.getDuracionHoras() < usuario.getTiempo() && 
			atraccion.getCupo() > 0).toList();
		
		}
		
	}
}
