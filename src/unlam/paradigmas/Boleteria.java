package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.factories.SesionFactory;
import unlam.paradigmas.modelos.Recibo;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;
import unlam.paradigmas.repositorios.promociones.IPromocionRepository;
import unlam.paradigmas.repositorios.recibos.IReciboRepository;

public class Boleteria {
	
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	private SesionFactory sesionFactory;
	private IReciboRepository reciboRepository;
	private static Boleteria instance;
	
	private Boleteria(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, IReciboRepository reciboRepository, SesionFactory sesionFactory) {
		this.atracciones = atraccionRepository.getAtracciones();
		this.promociones = promocionRepository.getPromociones();
		this.sesionFactory = sesionFactory;
		this.reciboRepository = reciboRepository;
	}
	
	public void atender(Usuario usuario) {
		 Sesion sesion = this.sesionFactory.create(usuario, atracciones, promociones);
		 Oferta oferta = sesion.generarOferta();
		 
		if(oferta == null) {
			System.out.println("\nNo existen ofertas disponibles para este usuario.");
		}

		 
		 while (oferta != null) {

			if(sesion.sugerir(oferta)) {
				sesion.aceptarOferta(oferta);
			} else {
				sesion.rechazarOferta(oferta);
			}
			
			oferta = sesion.generarOferta();
			if(oferta == null) {
				System.out.println("\nNo hay mas ofertas disponibles para este usuario.");
			}
		}
		 
		 Recibo recibo = sesion.getRecibo();
		 if(recibo.hayVentas()) {			 
			 this.reciboRepository.escribir(recibo);
		 }
	}
	
	public static Boleteria getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static Boleteria init(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, IReciboRepository reciboRepository ,SesionFactory sesionHandler) {
		if (instance == null) {
			instance = new Boleteria(atraccionRepository, promocionRepository, reciboRepository, sesionHandler);
		}
		return instance;
	}
	
	public static void setSesionHandler(SesionFactory sesionHandler) {
		instance.sesionFactory = sesionHandler;
	}
}
