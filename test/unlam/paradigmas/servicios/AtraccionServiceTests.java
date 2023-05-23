package unlam.paradigmas.servicios;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.Atraccion;
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
		atracciones.add(atraccion("LA COMARCA", 10.0, 20.0, 5, "DEGUSTACION"));
		atracciones.add(atraccion("MINAS TIRITH", 30.0, 70.0, 20, "PAISAJE"));
		atracciones.add(atraccion("MORDOR", 5.0, 10.0, 30, "AVENTURA"));
		Mockito.when(atraccionRepository.getAtracciones()).thenReturn(atracciones);
		
		List<Atraccion> atraccionesActuales = atraccionService.getAtracciones();
		
		assertEquals(atracciones, atraccionesActuales);
	}

	private Atraccion atraccion(String nombre, Double costo, Double duracion, Integer cupo, String tipoAtraccion) {
		Atraccion atraccion = new Atraccion();
		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setDuracionHoras(duracion);
		atraccion.setCupo(cupo);
		atraccion.setTipoAtraccion(tipoAtraccion);
		
		return atraccion;
	}
}
