package unlam.paradigmas.modelos.ofertas.promociones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import unlam.paradigmas.modelos.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class PromocionPorcentualTests {

	@Test
	public void dadaUnaPromocionPorcentual_AlCalcularElPrecio_SeAplicaElDescuento() {
		Atraccion atraccionDeCosto1000 = new Atraccion("Nombre", 1000.0, 10.0, 5, TipoActividad.AVENTURA);
		Promocion promocionPorcentual = new PromocionPorcentual(TipoActividad.AVENTURA, 10.0, Arrays.asList(atraccionDeCosto1000));
		
		Double precio = promocionPorcentual.getPrecio();
		
		assertEquals(900.0, precio);
	}
}
