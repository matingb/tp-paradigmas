package unlam.paradigmas;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LectorArchivosTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ILector lectorDeArchivos;

	public LectorArchivosTests() {
		lectorDeArchivos = new LectorArchivos();
	}

	@Test
	public void DadoUnArchivoConUsuarios_AlLeer_ObtengoUnaListaDeUsuarios() throws IOException {
		String path = dadoUnArchivoConContenido(
				"3|10|DEGUSTACION|MATI GARCIA\n" + "7|11|AVENTURA|AGOS MOTTU\n" + "12|12|AVENTURA|FEDE CASTRO\n");

		List<Usuario> usuarios = lectorDeArchivos.leerUsuarios(path);

		validaUsuario(3, 10, "DEGUSTACION", "MATI GARCIA", usuarios.get(0));
		validaUsuario(7, 11, "AVENTURA", "AGOS MOTTU", usuarios.get(1));
		validaUsuario(12, 12, "AVENTURA", "FEDE CASTRO", usuarios.get(2));

	}

	@Test
	public void DadoUnArchivoDeUsuariosVacio_AlLeer_NoObtengoUsuarios() throws IOException {
		String path = dadoUnArchivoConContenido("");

		List<Usuario> usuarios = lectorDeArchivos.leerUsuarios(path);

		assertEquals(0, usuarios.size());
	}
	
	private void validaUsuario(double presupuesto, double tiempo, String actividadFavorita, String nombre, Usuario usuario) {
		assertEquals(presupuesto, usuario.getPresupuesto(), 0);
		assertEquals(tiempo, usuario.getTiempo(), 0);
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
