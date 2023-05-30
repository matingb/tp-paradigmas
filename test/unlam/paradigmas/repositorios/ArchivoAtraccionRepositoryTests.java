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

public class ArchivoAtraccionRepositoryTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ArchivoAtraccionRepository repository;

	private static Properties properties = Mockito.mock(Properties.class);

	public ArchivoAtraccionRepositoryTests() {
		repository = ArchivoAtraccionRepository.init(properties);
	}

	@Test
	public void DadoUnArchivoConAtracciones_AlLeer_ObtengoUnaListaDeAtracciones() throws IOException {
		dadoUnArchivoConContenido(
			"LA COMARCA|30|1|20|DEGUSTACION\n" + 
			"MINAS TIRITH|7|11|7|PAISAJE\n" + 
			"MORDOR|12|12|2|AVENTURA\n");
		
		List<Atraccion> atraccion = repository.getAtracciones();

		validarAtraccion("LA COMARCA", 30.0, 1.0, 20, "DEGUSTACION", atraccion.get(0));
		validarAtraccion("MINAS TIRITH", 7.0, 11.0, 7, "PAISAJE", atraccion.get(1));
		validarAtraccion("MORDOR", 12.0, 12.0, 2, "AVENTURA", atraccion.get(2));

	}

	@Test
	public void DadoUnArchivoDeAtraccionesVacio_AlLeer_NoObtengoAtracciones() throws IOException {
		dadoUnArchivoConContenido("");

		List<Atraccion> atracciones = repository.getAtracciones();

		assertEquals(0, atracciones.size());
	}
	
	private void validarAtraccion(String nombre, Double costo, Double duracionHoras, Integer cupo, String tipoAtraccion,
			Atraccion atraccion) {
		assertEquals(nombre, atraccion.getNombre());
		assertEquals(costo, atraccion.getCosto());
		assertEquals(duracionHoras, atraccion.getDuracionHoras());
		assertEquals(cupo, atraccion.getCupo());
		assertEquals(tipoAtraccion, (atraccion.getTipoAtraccion().name()));

	}

	private void dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal" + new Random().nextInt() +".in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();
		
		Mockito.when(properties.getProperty("PathAtracciones")).thenReturn(inputFile.getAbsolutePath());
	}
}