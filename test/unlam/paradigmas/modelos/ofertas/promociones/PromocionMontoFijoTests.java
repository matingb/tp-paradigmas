package unlam.paradigmas.modelos.ofertas.promociones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class PromocionMontoFijoTests {
	
	@Test
	public void dadaUnaPromocionCon3Atracciones_AlObtenerElPrecio_ObtieneElPrecioFijo() {
		Atraccion atraccionPrueba1 = new Atraccion("Nombre1", 50.0, 6.0, 5, TipoActividad.AVENTURA);
		Atraccion atraccionPrueba2 = new Atraccion("Nombre2", 75.0, 5.0, 5, TipoActividad.AVENTURA);
		Atraccion atraccionPrueba3 = new Atraccion("Nombre3", 20.0, 10.0, 5, TipoActividad.AVENTURA);
		Promocion promocion = new PromocionMontoFijo(TipoActividad.AVENTURA, 35.0, Arrays.asList(atraccionPrueba1, atraccionPrueba2, atraccionPrueba3));
		
		Double precio = promocion.getPrecio();
		
		assertEquals(35.0, precio);
	}

}
