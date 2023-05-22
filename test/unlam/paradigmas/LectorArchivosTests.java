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
	public void DadoUnArchivoConUnUsuario_AlLeer_ObtengoUnaListaConEseUsuario() throws IOException {
		String path = dadoUnArchivoConContenido("3|10|DEGUSTACION|MATI GARCIA");

		List<Usuario> usuarios = lectorDeArchivos.leerUsuarios(path);

		assertEquals(3, usuarios.get(0).presupuesto, 0);
		assertEquals(10, usuarios.get(0).tiempo, 0);
		assertEquals("DEGUSTACION", usuarios.get(0).actividadFavorita);
		assertEquals("MATI GARCIA", usuarios.get(0).nombre);
	}

	private String dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal.in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		return inputFile.getAbsolutePath();
	}
}
