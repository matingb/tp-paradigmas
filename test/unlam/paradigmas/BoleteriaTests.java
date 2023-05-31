package unlam.paradigmas;

import static org.junit.Assert.assertEquals;
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

	private Boleteria boleteria;
	private IAtraccionRepository atraccionRepository = Mockito.mock(IAtraccionRepository.class);
	private IPromocionRepository promocionRepository = Mockito.mock(IPromocionRepository.class);
	private Ofertador ofertador = Mockito.mock(Ofertador.class);
	private Sugeridor sugeridor;
	
	
	public BoleteriaTests() {
		this.atraccionRepository = Mockito.mock(IAtraccionRepository.class);
		
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("LA COMARCA", 30.0, 12.0, 20, TipoActividad.DEGUSTACION));
		atracciones.add(new Atraccion("MINAS TIRITH", 7.0, 11.0, 7, TipoActividad.PAISAJE));
		atracciones.add(new Atraccion("MORIA", 12.0, 12.0, 2, TipoActividad.AVENTURA));
		atracciones.add(new Atraccion("BOSQUE NEGRO", 2.0, 22.0, 5, TipoActividad.AVENTURA));
		atracciones.add(new Atraccion("MORDOR", 6.0, 32.0, 3, TipoActividad.AVENTURA));
		Mockito.when(atraccionRepository.getAtracciones()).thenReturn(atracciones);
		
		this.promocionRepository = Mockito.mock(IPromocionRepository.class);
		this.ofertador = Mockito.mock(Ofertador.class);
		this.sugeridor = Mockito.mock(Sugeridor.class);
		boleteria = new Boleteria(atraccionRepository, promocionRepository, ofertador, sugeridor);
	}
	
	@Test
	public void DadoUnUsuarioConPreferenciaAventura_AlAtenderlo_DebeBuscarOfertasParaLasAtraccionesDeAventura() {
		Usuario usuario = new Usuario();
		usuario.setPresupuesto(50.0);
		usuario.setTiempo(20.0);
		usuario.setActividadFavorita(TipoActividad.AVENTURA);
		
		boleteria.atender(usuario);
		
        ArgumentCaptor<List<Atraccion>> captor = ArgumentCaptor.forClass(List.class);
        verify(ofertador).generarOfertaDeAtraccion(usuario, captor.capture());

        List<Atraccion> atraccionesArgument = captor.getValue();
        for (Atraccion atraccion : atraccionesArgument) {
            assertEquals(TipoActividad.AVENTURA, atraccion.getTipoActividad());
        }
	}
}
