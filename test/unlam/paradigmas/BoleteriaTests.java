package unlam.paradigmas;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.TipoActividad;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.IAtraccionRepository;
import unlam.paradigmas.repositorios.IPromocionRepository;

public class BoleteriaTests {

	private IAtraccionRepository atraccionRepository;
	private IPromocionRepository promocionRepository;
	private Ofertador ofertador;
	private Sugeridor sugeridor;
	
	
	public BoleteriaTests() {
		this.promocionRepository = Mockito.mock(IPromocionRepository.class);
		this.ofertador = Mockito.mock(Ofertador.class);
		this.sugeridor = Mockito.mock(Sugeridor.class);
	}
	
	@Test
	public void DadoUnUsuarioConPreferenciaAventura_AlAtenderlo_DebeBuscarOfertasParaLasAtraccionesDeAventura() {
		List<Atraccion> atracciones = dadaUnaListaDeAtracciones();
		Usuario usuario = dadoUnUsuarioCon(50.0, 20.0, TipoActividad.AVENTURA);
		Mockito.when(ofertador.generarOfertaDeAtraccion(usuario, atracciones)).thenReturn(null);
		Boleteria boleteria = new Boleteria(atraccionRepository, promocionRepository, ofertador, sugeridor);
			
		boleteria.atender(usuario);
		
		ArgumentCaptor<Usuario> captorUsuario = ArgumentCaptor.forClass(Usuario.class);
        ArgumentCaptor<List<Atraccion>> captorAtraccion = ArgumentCaptor.forClass(List.class);
        verify(ofertador).generarOfertaDeAtraccion(captorUsuario.capture(), captorAtraccion.capture());
        for (Atraccion atraccion : captorAtraccion.getValue()) {
            assertEquals(TipoActividad.AVENTURA, atraccion.getTipoActividad());
        }
	}
	
	@Test
	public void DadoUnUsuarioConOfertasPosibles_AlAtenderlo_SeLeDebeSugerirLaOferta() {
		List<Atraccion> atracciones = dadaUnaListaDeAtracciones();
		Usuario usuario = dadoUnUsuarioCon(50.0, 20.0, TipoActividad.AVENTURA);
		Mockito.when(ofertador.generarOfertaDeAtraccion(any(Usuario.class), anyList())).thenReturn(atracciones.get(0)).thenReturn(null);
		Boleteria boleteria = new Boleteria(atraccionRepository, promocionRepository, ofertador, sugeridor);
			
		boleteria.atender(usuario);
		
        verify(sugeridor).sugerir(atracciones.get(0));
	}

	private Usuario dadoUnUsuarioCon(Double presupuesto, Double tiempo, TipoActividad actividadFavorita) {
		Usuario usuario = new Usuario();
		usuario.setPresupuesto(presupuesto);
		usuario.setTiempo(tiempo);
		usuario.setActividadFavorita(actividadFavorita);
		return usuario;
	}

	private List<Atraccion> dadaUnaListaDeAtracciones() {
		atraccionRepository = Mockito.mock(IAtraccionRepository.class);
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("LA COMARCA", 30.0, 12.0, 20, TipoActividad.DEGUSTACION));
		atracciones.add(new Atraccion("MINAS TIRITH", 7.0, 11.0, 7, TipoActividad.PAISAJE));
		atracciones.add(new Atraccion("MORIA", 12.0, 12.0, 2, TipoActividad.AVENTURA));
		atracciones.add(new Atraccion("BOSQUE NEGRO", 2.0, 22.0, 5, TipoActividad.AVENTURA));
		atracciones.add(new Atraccion("MORDOR", 6.0, 32.0, 3, TipoActividad.AVENTURA));
		Mockito.when(atraccionRepository.getAtracciones()).thenReturn(atracciones);
		return atracciones;
	}
}
