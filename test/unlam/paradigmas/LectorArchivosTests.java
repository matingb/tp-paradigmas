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
		String path = dadoUnArchivoConContenido(
				"3|10|DEGUSTACION|MATI GARCIA\n" + "7|11|AVENTURA|AGOS MOTTU\n" + "12|12|AVENTURA|FEDE CASTRO\n");

		List<Usuario> usuarios = lectorDeArchivos.leerUsuarios(path);

		assertEquals(3, usuarios.get(0).presupuesto, 0);
		assertEquals(10, usuarios.get(0).tiempo, 0);
		assertEquals("DEGUSTACION", usuarios.get(0).actividadFavorita);
		assertEquals("MATI GARCIA", usuarios.get(0).nombre);
		
		assertEquals(7, usuarios.get(1).presupuesto, 0);
		assertEquals(11, usuarios.get(1).tiempo, 0);
		assertEquals("AVENTURA", usuarios.get(1).actividadFavorita);
		assertEquals("AGOS MOTTU", usuarios.get(1).nombre);
		
		assertEquals(12, usuarios.get(2).presupuesto, 0);
		assertEquals(12, usuarios.get(2).tiempo, 0);
		assertEquals("AVENTURA", usuarios.get(2).actividadFavorita);
		assertEquals("FEDE CASTRO", usuarios.get(2).nombre);
	}

	private String dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal.in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		return inputFile.getAbsolutePath();
	}
}
