package unlam.paradigmas.repositorios;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.PromocionMontoFijo;
import unlam.paradigmas.modelos.TipoAtraccion;
import unlam.paradigmas.modelos.TipoPromocion;
import unlam.paradigmas.modelos.Usuario;

public class ArchivoRepositoryTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ArchivoRepository repository;

	private static Properties properties = Mockito.mock(Properties.class);

	public ArchivoRepositoryTests() {
		repository = ArchivoRepository.init(properties);
	}

	@Test
	public void DadoUnArchivoConUsuarios_AlLeer_ObtengoUnaListaDeUsuarios() throws IOException {
		String path = dadoUnArchivoConContenido(
				"3|10|DEGUSTACION|MATI GARCIA\n" + "7|11|AVENTURA|AGOS MOTTU\n" + "12|12|AVENTURA|FEDE CASTRO\n");
		Mockito.when(properties.getProperty("PathUsuarios")).thenReturn(path);

		List<Usuario> usuarios = repository.getUsuarios();

		validarUsuario(3.0, 10.0, "DEGUSTACION", "MATI GARCIA", usuarios.get(0));
		validarUsuario(7.0, 11.0, "AVENTURA", "AGOS MOTTU", usuarios.get(1));
		validarUsuario(12.0, 12.0, "AVENTURA", "FEDE CASTRO", usuarios.get(2));

	}

	@Test
	public void DadoUnArchivoDeUsuariosVacio_AlLeer_NoObtengoUsuarios() throws IOException {
		String path = dadoUnArchivoConContenido("");
		Mockito.when(properties.getProperty("PathUsuarios")).thenReturn(path);

		List<Usuario> usuarios = repository.getUsuarios();

		assertEquals(0, usuarios.size());
	}

	@Test
	public void DadoUnArchivoConAtracciones_AlLeer_ObtengoUnaListaDeAtracciones() throws IOException {
		String path = dadoUnArchivoConContenido(
				"LA COMARCA|30|1|20|DEGUSTACION\n" + "MINAS TIRITH|7|11|7|PAISAJE\n" + "MORDOR|12|12|2|AVENTURA\n");
		Mockito.when(properties.getProperty("PathAtracciones")).thenReturn(path);

		List<Atraccion> atraccion = repository.getAtracciones();

		validarAtraccion("LA COMARCA", 30.0, 1.0, 20, "DEGUSTACION", atraccion.get(0));
		validarAtraccion("MINAS TIRITH", 7.0, 11.0, 7, "PAISAJE", atraccion.get(1));
		validarAtraccion("MORDOR", 12.0, 12.0, 2, "AVENTURA", atraccion.get(2));

	}

	@Test
	public void DadoUnArchivoDeAtraccionesVacio_AlLeer_NoObtengoAtracciones() throws IOException {
		String path = dadoUnArchivoConContenido("");
		Mockito.when(properties.getProperty("PathAtracciones")).thenReturn(path);

		List<Atraccion> atracciones = repository.getAtracciones();

		assertEquals(0, atracciones.size());
	}

	@Test
	public void DadoUnArchivoConPromociones_AlLeer_ObtengoUnaListaDePromociones() throws IOException {
		String pathAtracciones = dadoUnArchivoConContenido(
				"LA COMARCA|30|1|20|DEGUSTACION\n" + 
				"MINAS TIRITH|7|11|7|PAISAJE\n" + 
				"MORIA|12|12|2|AVENTURA\n" +
				"BOSQUE NEGRO|12|12|2|AVENTURA\n" +
				"LOTHLORIEN|12|12|2|DEGUSTACION\n" +
				"EREBOR|12|12|2|PAISAJE\n" +
				"ABISMO DE HLEM|12|12|2|PAISAJE\n");
		Mockito.when(properties.getProperty("PathAtracciones")).thenReturn(pathAtracciones);
		
		List<Atraccion> atraccionesVigentes = repository.getAtracciones();
		
		String pathPromociones = dadoUnArchivoConContenido(
				"MONTO FIJO|AVENTURA|50|MORIA-BOSQUE NEGRO\n" +
				"PORCENTUAL|DEGUSTACION|10|LA COMARCA-LOTHLORIEN\n" +
				"COMBO|PAISAJE|1|ABISMO DE HLEM-EREBOR");
		Mockito.when(properties.getProperty("PathPromociones")).thenReturn(pathPromociones);

		List<Promocion> promociones = repository.getPromociones(atraccionesVigentes);
		
			
		for (Promocion p : promociones) {
			System.out.println(p.toString());
		}		
	}
	
	private void validarAtraccion(String nombre, Double costo, Double duracionHoras, Integer cupo, String tipoAtraccion,
			Atraccion atraccion) {
		assertEquals(nombre, atraccion.getNombre());
		assertEquals(costo, atraccion.getCosto());
		assertEquals(duracionHoras, atraccion.getDuracionHoras());
		assertEquals(cupo, atraccion.getCupo());
		assertEquals(tipoAtraccion, (atraccion.getTipoAtraccion().name()));

	}

	private void validarUsuario(Double presupuesto, Double tiempo, String actividadFavorita, String nombre,
			Usuario usuario) {
		assertEquals(presupuesto, usuario.getPresupuesto());
		assertEquals(tiempo, usuario.getTiempo());
		assertEquals(actividadFavorita, usuario.getActividadFavorita());
		assertEquals(nombre, usuario.getNombre());
	}

	private String dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal" + new Random().nextInt() +".in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		return inputFile.getAbsolutePath();
	}
}
