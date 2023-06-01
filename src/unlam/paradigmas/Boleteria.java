package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.repositorios.atraccionRepository.IAtraccionRepository;
import unlam.paradigmas.repositorios.promocionRepository.IPromocionRepository;

public class Boleteria {
	
	List<Atraccion> atracciones;
	List<Promocion> promociones;
	SesionHandler sesionHandler;
	
	public Boleteria(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, SesionHandler sesionHandler) {
		this.atracciones = atraccionRepository.getAtracciones();
		this.promociones = promocionRepository.getPromociones();
		this.sesionHandler = sesionHandler;
	}
	
	public void atender(Usuario usuario) {
		Sesion sesion = this.sesionHandler.generarSesion(usuario, atracciones, promociones);

		Oferta oferta = sesion.generarOferta();
		
		while (oferta != null) {

			if(sesion.sugerir(oferta)) {
				sesion.aceptarOferta(oferta);
			}

			oferta = sesion.generarOferta();
		}
	}
}
