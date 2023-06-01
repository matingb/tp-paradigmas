package unlam.paradigmas;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.TipoActividad;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionMontoFijo;

public class SesionTests {
	
	@Test
	public void dadasPromocionesYAtracciones_AlGenerarOfertas_EntoncesObtengoUnaPromocionDeLaPreferenciaDelUsuario() {
		List<Atraccion> atracciones = Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA),atraccionDeTipo(TipoActividad.DEGUSTACION));
		List<Promocion> promociones = Arrays.asList(
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))),
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))));
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.AVENTURA);
		Sesion sesion = new Sesion(usuario, atracciones, promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertInstanceOf(Promocion.class, oferta);
		assertEquals(TipoActividad.AVENTURA, oferta.getTipoActividad());
	}
	
	@Test
	public void dadoQueNoHayPromocionesPreferidasPorElUsuario_AlGenerarOfertas_EntonceObtengoUnaAtraccionPreferida() {
		List<Atraccion> atracciones = Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA), atraccionDeTipo(TipoActividad.DEGUSTACION));
		List<Promocion> promociones = Arrays.asList(
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))),
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))));
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertInstanceOf(Atraccion.class, oferta);
		assertEquals(TipoActividad.DEGUSTACION, oferta.getTipoActividad());
	}
	
	@Test
	public void dadoQueNoHayPromocionesNiAtraccionesPreferidasPorElUsuario_AlGenerarOfertas_EntonceObtengoUnaPromocionNoPreferida() {
		List<Atraccion> atracciones = Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA), atraccionDeTipo(TipoActividad.PAISAJE));
		List<Promocion> promociones = Arrays.asList(
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))),
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))));
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertInstanceOf(Promocion.class, oferta);
		assertEquals(TipoActividad.AVENTURA, oferta.getTipoActividad());
	}
	
	@Test
	public void dadoQueNoHayPromocionesDisponiblesNiAtraccionesPreferidasPorElUsuario_AlGenerarOfertas_EntonceObtengoUnaAtraccionNoPreferida() {
		List<Atraccion> atracciones = Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA));
		List<Promocion> promociones = Arrays.asList();
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertInstanceOf(Atraccion.class, oferta);
		assertEquals(TipoActividad.AVENTURA, oferta.getTipoActividad());
	}
	
	@Test
	public void dadoQueElUsuarioNoTienePresupuesto_AlGenerarOfertas_EntoncesNoObtieneOfertas() {
		List<Atraccion> atracciones = Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA), atraccionDeTipo(TipoActividad.PAISAJE));
		List<Promocion> promociones = Arrays.asList(
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))),
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))));
		Usuario usuario = new Usuario("Nombre", 0.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertNull(oferta);
	}
	
	@Test
	public void dadoQueElUsuarioNoTieneTiempo_AlGenerarOfertas_EntoncesNoObtieneOfertas() {
		List<Atraccion> atracciones = Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA), atraccionDeTipo(TipoActividad.PAISAJE));
		List<Promocion> promociones = Arrays.asList(
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))),
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(atraccionDeTipo(TipoActividad.AVENTURA))));
		Usuario usuario = new Usuario("Nombre", 50.0, 0.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertNull(oferta);
	}
	
	@Test
	public void dadoQueLaAtraccionNoTieneCupo_AlGenerarOfertas_EntoncesNoObtieneOfertas() {
		List<Atraccion> atracciones = Arrays.asList(new Atraccion("Nombre", 1.0, 1.0, 0, TipoActividad.AVENTURA));
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, Arrays.asList());
		
		Oferta oferta = sesion.generarOferta();
		
		assertNull(oferta);
	}
	
	@Test
	public void dadoQueLaPromocionNoTieneCupo_AlGenerarOfertas_EntoncesNoObtieneOfertas() {
		List<Promocion> promociones = Arrays.asList(
				new PromocionMontoFijo(TipoActividad.AVENTURA, 1.0, Arrays.asList(new Atraccion("Nombre", 1.0, 1.0, 0, TipoActividad.AVENTURA))));
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, Arrays.asList(), promociones);
		
		Oferta oferta = sesion.generarOferta();
		
		assertNull(oferta);
	}
	
	@Test
	public void dadaUnaOferta_AlAceptarla_ElUsuarioDebeDescontarSuPrecioYTiempo() {
		Oferta oferta = new Atraccion("Nombre", 20.0, 15.0, 1, TipoActividad.AVENTURA);
		Usuario usuario = Mockito.mock(Usuario.class);
		Sesion sesion = new Sesion(usuario, Arrays.asList(), Arrays.asList());
		
		sesion.aceptarOferta(oferta);
		
		verify(usuario).pagarBoleteria(20);
		verify(usuario).reducirTiempo(15);
	}
	
	@Test
	public void dadaUnaOferta_AlAceptarla_LaOfertaDebeDescontarSuCupo() {
		Oferta oferta = Mockito.mock(Oferta.class);
		Usuario usuario = Mockito.mock(Usuario.class);
		Sesion sesion = new Sesion(usuario, Arrays.asList(), Arrays.asList());
		
		sesion.aceptarOferta(oferta);
		
		verify(oferta).descontarCupo();
	}
	
	
	public Atraccion atraccionDeTipo(TipoActividad tipoActividad) {
		return new Atraccion("Nombre", 1.0, 1.0, 1, tipoActividad);
	}
}
