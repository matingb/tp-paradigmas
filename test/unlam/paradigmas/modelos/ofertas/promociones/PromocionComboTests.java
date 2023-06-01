package unlam.paradigmas.modelos.ofertas.promociones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class PromocionComboTests {

	@Test
	public void dadaUnaPromocionCon3AtraccionesY1Gratis_AlObtenerElPrecio_DescuentaElValorDeLaGratis() {
		Atraccion atraccionDeCosto750 = new Atraccion("Nombre", 750.0, 10.0, 5, TipoActividad.AVENTURA);
		Atraccion atraccionDeCosto500 = new Atraccion("Nombre", 500.0, 10.0, 5, TipoActividad.AVENTURA);
		Atraccion atraccionDeCosto1000 = new Atraccion("Nombre", 1000.0, 10.0, 5, TipoActividad.AVENTURA);
		Promocion promocion = new PromocionCombo(TipoActividad.AVENTURA, 1, Arrays.asList(atraccionDeCosto500, atraccionDeCosto750, atraccionDeCosto1000));
		
		Double precio = promocion.getPrecio();
		
		assertEquals(1750.0, precio);
	}
}
