package unlam.paradigmas;

import java.util.List;

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
		List<Atraccion> atraccionesPosibles = atracciones.stream().filter(atraccion -> 
			atraccion.getTipoActividad() == usuario.getActividadFavorita()).toList();
			
		
		while (!atraccionesPosibles.isEmpty()) {
			atraccionesPosibles = atracciones.stream().filter(atraccion ->
			atraccion.getCosto() < usuario.getPresupuesto() &&
			atraccion.getDuracionHoras() < usuario.getTiempo()).toList();
			
			Atraccion oferta = ofertador.generarOfertaDeAtraccion(atraccionesPosibles);
			if(sugeridor.sugerir(oferta)) {
				usuario.pagarBoleteria(oferta.getCosto());
				usuario.reducirTiempo(oferta.getDuracionHoras());
				
				oferta.reducirCupo();
			}
			
			atraccionesPosibles.remove(oferta);	
		}
		
		/*List<Atraccion> atraccionesNoPreferidas = atracciones.stream().filter(atraccion -> 
		atraccion.getTipoActividad() != usuario.getActividadFavorita() &&
		atraccion.getCosto() < usuario.getPresupuesto() &&
		atraccion.getDuracionHoras() < usuario.getTiempo()).toList();
		
		Atraccion oferta = ofertador.generarOfertaDeAtraccion(atraccionesPosibles);
		sugeridor.sugerir(oferta);*/
	}
}
