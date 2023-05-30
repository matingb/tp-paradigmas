package unlam.paradigmas.repositorios;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.PromocionCombo;
import unlam.paradigmas.modelos.PromocionMontoFijo;
import unlam.paradigmas.modelos.PromocionPorcentual;
import unlam.paradigmas.modelos.TipoAtraccion;
import unlam.paradigmas.modelos.TipoPromocion;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.servicios.AtraccionService;

public class ArchivoPromocionRepositoryTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ArchivoPromocionRepository repository;

	private static Properties properties = Mockito.mock(Properties.class);
	
	private static AtraccionService atraccionService = Mockito.mock(AtraccionService.class);

	public ArchivoPromocionRepositoryTests() {
		repository = ArchivoPromocionRepository.init(properties, atraccionService);
	}

	@Test
	public void DadoUnArchivoConPromociones_AlLeer_ObtengoUnaListaConLosDistintosTiposPromociones() throws IOException {
		dadaUnaListaDeAtracciones();
		dadoUnArchivoConContenido(
				"MONTO_FIJO|AVENTURA|50|MORIA-BOSQUE NEGRO-MORDOR\n" +
				"PORCENTUAL|DEGUSTACION|10|LA COMARCA-LOTHLORIEN\n" +
				"COMBO|PAISAJE|1|ABISMO DE HLEM-EREBOR");

		List<Promocion> promociones = repository.getPromociones();
		
		assertEquals(3, promociones.size());
		assertEquals(3, promociones.get(0).getAtraccionesIncluidas().size());
		
		List<Atraccion> atraccionesActuales = promociones.get(0).getAtraccionesIncluidas();
		assertEquals(new Atraccion("MORIA", 12.0, 12.0, 2, TipoAtraccion.AVENTURA), atraccionesActuales.get(0));
		assertEquals(new Atraccion("BOSQUE NEGRO", 2.0, 22.0, 5, TipoAtraccion.AVENTURA), atraccionesActuales.get(1));
		assertEquals(new Atraccion("MORDOR", 6.0, 32.0, 3, TipoAtraccion.AVENTURA), atraccionesActuales.get(2));
	}

	@Test
	public void DadoUnArchivoConPromocionDeMontoFijo_AlLeer_ObtengoUnaPromocionDelTipoMontoFijo() throws IOException {
		dadaUnaListaDeAtracciones();
		dadoUnArchivoConContenido("MONTO_FIJO|AVENTURA|50|MORIA-BOSQUE NEGRO");

		List<Promocion> promociones = repository.getPromociones();
		
		assertEquals(PromocionMontoFijo.class , promociones.get(0).getClass());
		PromocionMontoFijo promocionDeMontoFijo = (PromocionMontoFijo) promociones.get(0); 
		assertEquals(50, promocionDeMontoFijo.getPrecioFinal(), 0);
	}
	
	@Test
	public void DadoUnArchivoConPromocionPorcentual_AlLeer_ObtengoUnaPromocionDelTipoPorcentual() throws IOException {
		dadaUnaListaDeAtracciones();
		dadoUnArchivoConContenido("PORCENTUAL|DEGUSTACION|10|LA COMARCA-LOTHLORIEN");

		List<Promocion> promociones = repository.getPromociones();
		
		assertEquals(PromocionPorcentual.class , promociones.get(0).getClass());
		PromocionPorcentual promocionPorcentual = (PromocionPorcentual) promociones.get(0); 
		assertEquals(10, promocionPorcentual.getProcentajeDescuento(), 0);
	}
	
	@Test
	public void DadoUnArchivoConPromocionDeCombo_AlLeer_ObtengoUnaPromocionDelTipoCombo() throws IOException {
		dadaUnaListaDeAtracciones();
		dadoUnArchivoConContenido("COMBO|PAISAJE|1|ABISMO DE HLEM-EREBOR");

		List<Promocion> promociones = repository.getPromociones();
		
		assertEquals(PromocionCombo.class , promociones.get(0).getClass());
		PromocionCombo promocionDeCombo = (PromocionCombo) promociones.get(0); 
		assertEquals(1, promocionDeCombo.getCantAtraccionesGratis(), 0);
	}

	private String dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal" + new Random().nextInt() +".in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		String absolutPath = inputFile.getAbsolutePath();
		Mockito.when(properties.getProperty("PathPromociones")).thenReturn(absolutPath);
		
		return absolutPath;
	}
	
	private List<Atraccion> dadaUnaListaDeAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("LA COMARCA", 30.0, 12.0, 20, TipoAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("MINAS TIRITH", 7.0, 11.0, 7, TipoAtraccion.PAISAJE));
		atracciones.add(new Atraccion("MORIA", 12.0, 12.0, 2, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("BOSQUE NEGRO", 2.0, 22.0, 5, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("MORDOR", 6.0, 32.0, 3, TipoAtraccion.AVENTURA));
		
		for(Atraccion atraccion : atracciones) {			
			Mockito.when(atraccionService.getAtraccionByNombre(atraccion.getNombre())).thenReturn(atraccion);
		}
		
		return atracciones;
	}
}
