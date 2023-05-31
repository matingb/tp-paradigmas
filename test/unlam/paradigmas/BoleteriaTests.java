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

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Oferta;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.IAtraccionRepository;
import unlam.paradigmas.repositorios.IPromocionRepository;

public class BoleteriaTests {

	private IAtraccionRepository atraccionRepository;
	private IPromocionRepository promocionRepository;
	private Sesion sesion;
	private Boleteria boleteria;
	
	public BoleteriaTests() {
		this.atraccionRepository = Mockito.mock(IAtraccionRepository.class);
		this.promocionRepository = Mockito.mock(IPromocionRepository.class);
		SesionHandler sesionHandler = Mockito.mock(SesionHandler.class);
		this.boleteria = new Boleteria(atraccionRepository, promocionRepository, sesionHandler);

		this.sesion = Mockito.mock(Sesion.class);
		Mockito.when(sesionHandler.generarSesion(any(Usuario.class), anyList(), anyList())).thenReturn(sesion);
	}
	
	@Test
	public void DadoUnUsuarioSinOfertasPosibles_AlAtenderlo_NoSeLeMuestranSugerencias() {
		Usuario usuario = new Usuario();
		Mockito.when(this.sesion.generarOferta()).thenReturn(null);
			
		boleteria.atender(usuario);
		
		verify(sesion, never()).sugerir(any());
	}
	
	@Test
	public void DadoUnUsuarioConDosOfertasPosibles_AlAtenderlo_SeLeSugieronAmbas() {
		Usuario usuario = new Usuario();
		Oferta oferta1 = new Atraccion();
		Oferta oferta2 = new Atraccion();
		Mockito.when(this.sesion.generarOferta()).thenReturn(oferta1).thenReturn(oferta2).thenReturn(null);
			
		boleteria.atender(usuario);
		
		ArgumentCaptor<Oferta> ofertaCaptor = ArgumentCaptor.forClass(Oferta.class);
		verify(sesion, times(2)).sugerir(ofertaCaptor.capture());
		assertEquals(oferta1, ofertaCaptor.getValue());
		assertEquals(oferta2, ofertaCaptor.getValue());
	}
}
