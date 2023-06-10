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

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.Oferta;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionMontoFijo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionPorcentual;

public class SesionTests {
	
	public SesionTests() {
		Lector.init();
	}
	
	@Test
	public void dadoUnUsuarioConPreferencias_AlGenerarOfertas_EntoncesSeOfrecenEnElOrdenCorrespondiente() {
		TipoActividad actividadPreferida = TipoActividad.AVENTURA;
		TipoActividad actividadNoPreferida = TipoActividad.DEGUSTACION;
		Sesion sesion = new Sesion(usuarioDeActividad(actividadPreferida), 
				Arrays.asList(atraccionDeTipo(actividadPreferida),atraccionDeTipo(actividadNoPreferida)), 
				Arrays.asList(promocionDeTipoDeActividad(actividadPreferida), promocionDeTipoDeActividad(actividadNoPreferida)));
		
		List<Oferta> ofertas = cuandoSeGeneranLasOfertas(sesion);
		
		entoncesLaOfertaEsDelTipoYActividadEsperados(ofertas.get(0), Promocion.class, actividadPreferida);
		entoncesLaOfertaEsDelTipoYActividadEsperados(ofertas.get(1), Atraccion.class, actividadPreferida);
		entoncesLaOfertaEsDelTipoYActividadEsperados(ofertas.get(2), Promocion.class, actividadNoPreferida);
		entoncesLaOfertaEsDelTipoYActividadEsperados(ofertas.get(3), Atraccion.class, actividadNoPreferida);
	}

	@Test
	public void dadasPromocionesYAtracciones_AlGenerarOfertas_EntoncesObtengoUnaPromocionDeLaPreferenciaDelUsuario() {
		TipoActividad actividadPreferida = TipoActividad.AVENTURA;
		Sesion sesion = new Sesion(usuarioDeActividad(actividadPreferida), 
				Arrays.asList(atraccionDeTipo(actividadPreferida),atraccionDeTipo(TipoActividad.DEGUSTACION)), 
				Arrays.asList(promocionDeTipoDeActividad(actividadPreferida),promocionDeTipoDeActividad(TipoActividad.PAISAJE)));
		
		Oferta oferta = sesion.generarOferta();
		
		assertInstanceOf(Promocion.class, oferta);
		assertEquals(actividadPreferida, oferta.getTipoActividad());
	}
	
	@Test
	public void dadoQueNoHayPromocionesPreferidasPorElUsuario_AlGenerarOfertas_EntonceObtengoUnaAtraccionPreferida() {
		TipoActividad actividadPreferida = TipoActividad.DEGUSTACION;
		Sesion sesion = new Sesion(usuarioDeActividad(actividadPreferida), 
				Arrays.asList(atraccionDeTipo(actividadPreferida),atraccionDeTipo(TipoActividad.AVENTURA)), 
				Arrays.asList(promocionDeTipoDeActividad(TipoActividad.AVENTURA),promocionDeTipoDeActividad(TipoActividad.PAISAJE)));
		
		Oferta oferta = sesion.generarOferta();
		
		assertInstanceOf(Atraccion.class, oferta);
		assertEquals(actividadPreferida, oferta.getTipoActividad());
	}
	
	@Test
	public void dadoQueNoHayPromocionesNiAtraccionesPreferidasPorElUsuario_AlGenerarOfertas_EntonceObtengoUnaPromocionNoPreferida() {
		TipoActividad actividadPreferida = TipoActividad.PAISAJE;
		Sesion sesion = new Sesion(usuarioDeActividad(actividadPreferida), 
				Arrays.asList(atraccionDeTipo(TipoActividad.DEGUSTACION),atraccionDeTipo(TipoActividad.AVENTURA)), 
				Arrays.asList(promocionDeTipoDeActividad(TipoActividad.AVENTURA),promocionDeTipoDeActividad(TipoActividad.AVENTURA)));
		
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
	public void dadoUnaOfertaDeValorIgualAlPresupuesto_AlGenerarOfertas_SeLeOfreceLaOferta() {
		Atraccion ofertaEsperada = new Atraccion("Nombre", 50.0, 1.0, 1, TipoActividad.DEGUSTACION);
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, Arrays.asList(ofertaEsperada), Arrays.asList());
		
		Oferta oferta = sesion.generarOferta();
		
		assertEquals(ofertaEsperada, oferta);
	}
	
	@Test
	public void dadoUnaOfertaDeDuracionIgualAlTiempoDisponible_AlGenerarOfertas_SeLeOfreceLaOferta() {
		Atraccion ofertaEsperada = new Atraccion("Nombre", 1.0, 50.0, 1, TipoActividad.DEGUSTACION);
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, Arrays.asList(ofertaEsperada), Arrays.asList());
		
		Oferta oferta = sesion.generarOferta();
		
		assertEquals(ofertaEsperada, oferta);
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
	
	@Test
	public void dadasDosOfertas_traerLaDeMayorPrecio () {
		Atraccion ofertaCara = new Atraccion("Nombre", 50.0, 1.0, 1, TipoActividad.DEGUSTACION);
		Atraccion ofertaBarata = new Atraccion("Nombre", 20.0, 1.0, 1, TipoActividad.DEGUSTACION);
		Usuario usuario = new Usuario("Nombre", 50.0, 50.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, Arrays.asList(ofertaCara,ofertaBarata), Arrays.asList());
		
		Oferta oferta = sesion.generarOferta();
		
		assertEquals(ofertaCara, oferta);
	}
	
	@Test
	public void dadasDosOfertasConIgualPrecio_traerLaDeMayorTiempo () {
		Atraccion ofertaPocoTiempo = new Atraccion("Nombre", 50.0, 1.0, 1, TipoActividad.DEGUSTACION);
		Atraccion ofertaMuchoTiempo = new Atraccion("Nombre", 50.0, 60.0, 1, TipoActividad.DEGUSTACION);
		Usuario usuario = new Usuario("Nombre", 50.0, 70.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, Arrays.asList(ofertaPocoTiempo,ofertaMuchoTiempo), Arrays.asList());
		
		Oferta oferta = sesion.generarOferta();
		
		assertEquals(ofertaMuchoTiempo, oferta);
	}
	
	@Test
	public void dadoQueSeAceptaUnaPromocion_entoncesNoSeMuestranLasAtraccionesIncluidas () {
		Atraccion atraccionIncluida = new Atraccion("atraccionIncluida", 50.0, 30.0, 1, TipoActividad.DEGUSTACION);
		Atraccion atraccionNoIncluida = new Atraccion("atraccionNoIncluida", 40.0, 20.0, 1, TipoActividad.DEGUSTACION);
		
		List<Atraccion> atracciones = Arrays.asList(atraccionIncluida, atraccionNoIncluida);
		Promocion promocion = new PromocionMontoFijo(TipoActividad.DEGUSTACION, 1.0, Arrays.asList(atraccionIncluida));
		Usuario usuario = new Usuario("Nombre", 200.0, 200.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, atracciones, Arrays.asList(promocion));
		sesion.aceptarOferta(promocion);
		
		Oferta oferta = sesion.generarOferta();
		
		assertEquals(atraccionNoIncluida, oferta);
	}
	
	@Test
	public void dadoQueSeAceptaUnaPromocion_entoncesNoSeMuestraOtraPromocionQueTieneAtraccionesDeLaPrimera () {
		Atraccion atraccionIncluida = new Atraccion("atraccionIncluida", 50.0, 30.0, 1, TipoActividad.DEGUSTACION);
		Promocion promocionAceptada = new PromocionMontoFijo(TipoActividad.DEGUSTACION, 1.0, Arrays.asList(atraccionIncluida));
		Promocion promocionNoMostrar = new PromocionPorcentual(TipoActividad.DEGUSTACION, 25.0, Arrays.asList(atraccionIncluida));
		
		Usuario usuario = new Usuario("Nombre", 200.0, 200.0, TipoActividad.DEGUSTACION);
		Sesion sesion = new Sesion(usuario, Arrays.asList(atraccionIncluida), Arrays.asList(promocionAceptada, promocionNoMostrar));
		
		sesion.aceptarOferta(promocionAceptada);
		
		Oferta oferta = sesion.generarOferta();
		
		assertNull(oferta);
	}
	
	private Atraccion atraccionDeTipo(TipoActividad tipoActividad) {
		return new Atraccion("Nombre", 1.0, 1.0, 1, tipoActividad);
	}
	
	private PromocionMontoFijo promocionDeTipoDeActividad(TipoActividad actividadPreferida) {
		return new PromocionMontoFijo(actividadPreferida, 1.0, Arrays.asList(atraccionDeTipo(actividadPreferida)));
	}

	private Usuario usuarioDeActividad(TipoActividad actividadPreferida) {
		return new Usuario("Nombre", 50.0, 50.0, actividadPreferida);
	}

	private List<Oferta> cuandoSeGeneranLasOfertas(Sesion sesion) {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		for(int i=0; i<4; i++) {
			Oferta oferta = sesion.generarOferta();
			sesion.rechazarOferta(oferta);
			ofertas.add(oferta);
		}
		
		return ofertas;
	}
	
	private void entoncesLaOfertaEsDelTipoYActividadEsperados(Oferta oferta, Class<?> tipoOferta, TipoActividad tipoActividad) {
	    assertInstanceOf(tipoOferta, oferta);
	    assertEquals(tipoActividad, oferta.getTipoActividad());
	}
}
