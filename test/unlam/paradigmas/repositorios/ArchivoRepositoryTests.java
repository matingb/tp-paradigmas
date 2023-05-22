package unlam.paradigmas.repositorios;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.ArchivoRepository;

public class ArchivoRepositoryTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ArchivoRepository repository;

	public ArchivoRepositoryTests() {
		repository = new ArchivoRepository();
	}


	@Test
	public void DadoUnArchivoConUsuarios_AlLeer_ObtengoUnaListaDeUsuarios() throws IOException {
		String path = dadoUnArchivoConContenido(
				"3|10|DEGUSTACION|MATI GARCIA\n" + "7|11|AVENTURA|AGOS MOTTU\n" + "12|12|AVENTURA|FEDE CASTRO\n");

		List<Usuario> usuarios = repository.getUsuarios(path);

		validarUsuario(3.0, 10.0, "DEGUSTACION", "MATI GARCIA", usuarios.get(0));
		validarUsuario(7.0, 11.0, "AVENTURA", "AGOS MOTTU", usuarios.get(1));
		validarUsuario(12.0, 12.0, "AVENTURA", "FEDE CASTRO", usuarios.get(2));

	}

	@Test
	public void DadoUnArchivoDeUsuariosVacio_AlLeer_NoObtengoUsuarios() throws IOException {
		String path = dadoUnArchivoConContenido("");

		List<Usuario> usuarios = repository.getUsuarios(path);

		assertEquals(0, usuarios.size());
	}

	//PENDIENTE

	/*
	@Test
	public void DadoUnArchivoSinRespetarFormato_AlLeer_() throws IOException {
		String path = dadoUnArchivoConContenido(
				"3|10|DEGUSTACION|MATI GARCIA\n" + "7|11|AVENTURAAGOS MOTTU\n" + "12|12|AVENTURA|FEDE CASTRO\n");

		List<Usuario> usuarios = lectorDeArchivos.leerUsuarios(path);
	}*/

	@Test
	public void DadoUnArchivoConAtracciones_AlLeer_ObtengoUnaListaDeAtracciones() throws IOException {
		String path = dadoUnArchivoConContenido(
				"LA COMARCA|30|1|20|DEGUSTACION\n" + "MINAS TIRITH|7|11|7|PAISAJE\n" + "MORDOR|12|12|2|AVENTURA\n");

		List<Atraccion> atraccion = repository.getAtracciones(path);

		validarAtraccion("LA COMARCA", 30.0, 1.0, 20, "DEGUSTACION", atraccion.get(0));
		validarAtraccion("MINAS TIRITH" , 7.0, 11.0, 7, "PAISAJE", atraccion.get(1));
		validarAtraccion("MORDOR", 12.0, 12.0, 2, "AVENTURA", atraccion.get(2));

	}

	@Test
	public void DadoUnArchivoDeAtraccionesVacio_AlLeer_NoObtengoAtracciones() throws IOException {
		String path = dadoUnArchivoConContenido("");

		List<Atraccion> atracciones = repository.getAtracciones(path);

		assertEquals(0, atracciones.size());
	}

	private void validarAtraccion(String nombre, Double costo, Double duracionHoras, Integer cupo, String tipoAtraccion, Atraccion atraccion) {
		assertEquals(nombre, atraccion.getNombre());
		assertEquals(costo, atraccion.getCosto());
		assertEquals(duracionHoras, atraccion.getDuracionHoras());
		assertEquals(cupo, atraccion.getCupo());
		assertEquals(tipoAtraccion, atraccion.getTipoAtraccion());

	}

	private void validarUsuario(Double presupuesto, Double tiempo, String actividadFavorita, String nombre,
			Usuario usuario) {
		assertEquals(presupuesto, usuario.getPresupuesto());
		assertEquals(tiempo, usuario.getTiempo());
		assertEquals(actividadFavorita, usuario.getActividadFavorita());
		assertEquals(nombre, usuario.getNombre());
	}

	private String dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal.in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		return inputFile.getAbsolutePath();
	}
}
