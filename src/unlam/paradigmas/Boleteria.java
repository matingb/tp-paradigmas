package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;
import unlam.paradigmas.repositorios.promociones.IPromocionRepository;
import unlam.paradigmas.repositorios.recibos.IReciboRepository;
import unlam.paradigmas.services.SesionService;

public class Boleteria {
	
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	private SesionService sesionService;
	private IReciboRepository reciboRepository;
	private static Boleteria instance;
	
	private Boleteria(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, IReciboRepository reciboRepository, SesionService sesionService) {
		this.atracciones = atraccionRepository.getAtracciones();
		this.promociones = promocionRepository.getPromociones();
		this.sesionService = sesionService;
		this.reciboRepository = reciboRepository;
	}
	
	public void atender(Usuario usuario) {
		 Sesion sesion = this.sesionService.abrir(usuario, atracciones, promociones);

		 Oferta oferta = sesion.generarOferta();
		 
		 while (oferta != null) {

			if(sesion.sugerir(oferta)) {
				sesion.aceptarOferta(oferta);
			} else {
				sesion.rechazarOferta(oferta);
			}
			
			oferta = sesion.generarOferta();
		}
		 
		 this.reciboRepository.escribir(sesion.getVenta());
	}
	
	public static Boleteria getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static Boleteria init(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, IReciboRepository reciboRepository ,SesionService sesionHandler) {
		if (instance == null) {
			instance = new Boleteria(atraccionRepository, promocionRepository, reciboRepository, sesionHandler);
		}
		return instance;
	}
	
	public static void setSesionHandler(SesionService sesionHandler) {
		instance.sesionService = sesionHandler;
	}
}
