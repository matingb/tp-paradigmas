package unlam.paradigmas;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;
import unlam.paradigmas.repositorios.promociones.IPromocionRepository;

public class BoleteriaTests {

	private static IAtraccionRepository atraccionRepository = Mockito.mock(IAtraccionRepository.class);;
	private static IPromocionRepository promocionRepository = Mockito.mock(IPromocionRepository.class);;
	private Sesion sesion;
	private Boleteria boleteria;
	private Usuario usuario;
	
	public BoleteriaTests() {
		this.usuario = new Usuario();

		SesionHandler sesionHandler = Mockito.mock(SesionHandler.class);
		this.boleteria = Boleteria.init(atraccionRepository, promocionRepository, sesionHandler);
		Boleteria.setSesionHandler(sesionHandler);

		this.sesion = Mockito.mock(Sesion.class);
		Mockito.when(sesionHandler.generarSesion(any(Usuario.class), anyList(), anyList())).thenReturn(sesion);
	}
	
	@Test
	public void DadoUnUsuarioSinOfertasPosibles_AlAtenderlo_NoSeLeMuestranSugerencias() {
		Mockito.when(this.sesion.generarOferta()).thenReturn(null);
			
		boleteria.atender(usuario);
		
		verify(sesion, never()).sugerir(any());
	}
	
	@Test
	public void DadoUnUsuarioConDosOfertasPosibles_AlAtenderlo_SeLeSugieronAmbas() {
		Oferta oferta1 = dadaUnaAtraccion();
		Oferta oferta2 = dadaUnaAtraccion();
		Mockito.when(this.sesion.generarOferta()).thenReturn(oferta1).thenReturn(oferta2).thenReturn(null);
			
		boleteria.atender(usuario);
		
		ArgumentCaptor<Oferta> ofertaCaptor = ArgumentCaptor.forClass(Oferta.class);
		verify(sesion, times(2)).sugerir(ofertaCaptor.capture());
		assertEquals(oferta1, ofertaCaptor.getValue());
		assertEquals(oferta2, ofertaCaptor.getValue());
	}
	
	@Test
	public void DadoUnUsuarioConUnOferta_AlAceptarLaSugerencia_LaSesionAceptaLaOferta() {
		Oferta oferta1 = dadaUnaAtraccion();
		Mockito.when(this.sesion.generarOferta()).thenReturn(oferta1).thenReturn(null);
		Mockito.when(this.sesion.sugerir(oferta1)).thenReturn(true);
		
		boleteria.atender(usuario);
		
		verify(sesion, times(1)).aceptarOferta(oferta1);
	}
	
	@Test
	public void DadoUnUsuarioConUnOferta_AlRechazarLaSugerencia_LaSesionRechazaLaOferta() {
		Oferta oferta1 = dadaUnaAtraccion();
		Mockito.when(this.sesion.generarOferta()).thenReturn(oferta1).thenReturn(null);
		Mockito.when(this.sesion.sugerir(oferta1)).thenReturn(false);
		
		boleteria.atender(usuario);
		
		verify(sesion, times(1)).rechazarOferta(oferta1);
	}
	
	public Atraccion dadaUnaAtraccion() {
		return new Atraccion("Nombre", 50.0, 10.0, 5, TipoActividad.AVENTURA);
	}
}
