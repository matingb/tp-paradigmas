package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;
import unlam.paradigmas.repositorios.promociones.IPromocionRepository;

public class Boleteria {
	
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	private SesionHandler sesionHandler;
	private static Boleteria instance;
	
	private Boleteria(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, SesionHandler sesionHandler) {
		this.atracciones = atraccionRepository.getAtracciones();
		this.promociones = promocionRepository.getPromociones();
		this.sesionHandler = sesionHandler;
	}
	
	public void atender(Usuario usuario) {
		 Sesion sesion = this.sesionHandler.generarSesion(usuario, atracciones, promociones);
		 
		 //creo el objeto venta para crear el archivo de salida por cada usuario
		 Venta venta = new Venta(usuario);
		 venta.crearArchivo();

		 Oferta oferta = sesion.generarOferta();
		 
		 while (oferta != null) {

			if(sesion.sugerir(oferta)) {
				//aceptarOferta tiene que mandar como parametro a venta
				sesion.aceptarOferta(oferta,venta);
			} else {
				sesion.rechazarOferta(oferta);
			}
			
			oferta = sesion.generarOferta();
		}
		
		//escribir totales en el archivo y cerrarlo	
		venta.escribirTotales();
	}
	
	public static Boleteria getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static Boleteria init(IAtraccionRepository atraccionRepository, IPromocionRepository promocionRepository, SesionHandler sesionHandler) {
		if (instance == null) {
			instance = new Boleteria(atraccionRepository, promocionRepository, sesionHandler);
		}
		return instance;
	}
	
	public static void setSesionHandler(SesionHandler sesionHandler) {
		instance.sesionHandler = sesionHandler;
	}
}
