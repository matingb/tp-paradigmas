package unlam.paradigmas.servicios;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.TipoAtraccion;
import unlam.paradigmas.repositorios.IAtraccionRepository;

public class AtraccionServiceTests {

	private static IAtraccionRepository atraccionRepository = Mockito.mock(IAtraccionRepository.class);
	
	private AtraccionService atraccionService;
	
	public AtraccionServiceTests() {
		atraccionService = AtraccionService.init(atraccionRepository);
	}
	
	@Test
	public void dados3Usuarios_AlObtenerUsuarios_DeberiaObtenerLosUsuarios() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion("LA COMARCA", 10.0, 20.0, 5, TipoAtraccion.DEGUSTACION));
		atracciones.add(atraccion("MINAS TIRITH", 30.0, 70.0, 20, TipoAtraccion.PAISAJE));
		atracciones.add(atraccion("MORDOR", 5.0, 10.0, 30, TipoAtraccion.AVENTURA));
		Mockito.when(atraccionRepository.getAtracciones()).thenReturn(atracciones);
		
		List<Atraccion> atraccionesActuales = atraccionService.getAtracciones();
		
		assertEquals(atracciones, atraccionesActuales);
	}

	private Atraccion atraccion(String nombre, Double costo, Double duracion, Integer cupo, TipoAtraccion tipoAtraccion) {
		return new Atraccion(nombre, costo, duracion, cupo, tipoAtraccion);
	}
}
