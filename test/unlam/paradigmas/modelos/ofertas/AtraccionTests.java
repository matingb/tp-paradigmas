package unlam.paradigmas.modelos.ofertas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unlam.paradigmas.enums.TipoActividad;

public class AtraccionTests {

	@Test
	public void dadaUnaAtraccion_AlReducirSuCupo_SeReduceEn1() {
		Integer cupoActual = 5;
		Atraccion atraccion = new Atraccion("Nombre", 50.0, 10.0, cupoActual, TipoActividad.AVENTURA);
		
		atraccion.descontarCupo();
		
		assertEquals(cupoActual -1 , atraccion.getCupo());
	}
}
