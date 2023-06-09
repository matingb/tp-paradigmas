package unlam.paradigmas.repositorios.promociones;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionCombo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionMontoFijo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionPorcentual;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;

public class ArchivoPromocionRepositoryTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ArchivoPromocionRepository repository;

	private static Properties properties = Mockito.mock(Properties.class);
	
	private static IAtraccionRepository atraccionRepository = Mockito.mock(IAtraccionRepository.class);

	public ArchivoPromocionRepositoryTests() {
		repository = ArchivoPromocionRepository.init(properties, atraccionRepository);
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
		assertEquals(new Atraccion("MORIA", 12.0, 12.0, 2, TipoActividad.AVENTURA), atraccionesActuales.get(0));
		assertEquals(new Atraccion("BOSQUE NEGRO", 2.0, 22.0, 5, TipoActividad.AVENTURA), atraccionesActuales.get(1));
		assertEquals(new Atraccion("MORDOR", 6.0, 32.0, 3, TipoActividad.AVENTURA), atraccionesActuales.get(2));
	}

	@Test
	public void DadoUnArchivoConPromocionDeMontoFijo_AlLeer_ObtengoUnaPromocionDelTipoMontoFijo() throws IOException {
		dadaUnaListaDeAtracciones();
		dadoUnArchivoConContenido("MONTO_FIJO|AVENTURA|50|MORIA-BOSQUE NEGRO");

		List<Promocion> promociones = repository.getPromociones();
		
		assertEquals(PromocionMontoFijo.class , promociones.get(0).getClass());
		PromocionMontoFijo promocionDeMontoFijo = (PromocionMontoFijo) promociones.get(0); 
		assertEquals(50, promocionDeMontoFijo.getPrecio(), 0);
	}
	
	@Test
	public void DadoUnArchivoConPromocionPorcentual_AlLeer_ObtengoUnaPromocionDelTipoPorcentual() throws IOException {
		dadaUnaListaDeAtracciones();
		dadoUnArchivoConContenido("PORCENTUAL|DEGUSTACION|10|LA COMARCA-LOTHLORIEN");

		List<Promocion> promociones = repository.getPromociones();
		
		assertEquals(PromocionPorcentual.class , promociones.get(0).getClass());
		PromocionPorcentual promocionPorcentual = (PromocionPorcentual) promociones.get(0); 
		assertEquals(10, promocionPorcentual.getPorcentajeDescuento(), 0);
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

	private void dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal" + new Random().nextInt() +".in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		Mockito.when(properties.getProperty("PathPromociones")).thenReturn(inputFile.getAbsolutePath());
	}
	
	private List<Atraccion> dadaUnaListaDeAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("LA COMARCA", 30.0, 12.0, 20, TipoActividad.DEGUSTACION));
		atracciones.add(new Atraccion("MINAS TIRITH", 7.0, 11.0, 7, TipoActividad.PAISAJE));
		atracciones.add(new Atraccion("MORIA", 12.0, 12.0, 2, TipoActividad.AVENTURA));
		atracciones.add(new Atraccion("BOSQUE NEGRO", 2.0, 22.0, 5, TipoActividad.AVENTURA));
		atracciones.add(new Atraccion("MORDOR", 6.0, 32.0, 3, TipoActividad.AVENTURA));
		
		for(Atraccion atraccion : atracciones) {			
			Mockito.when(atraccionRepository.getAtraccionByNombre(atraccion.getNombre())).thenReturn(atraccion);
		}
		
		return atracciones;
	}
}
