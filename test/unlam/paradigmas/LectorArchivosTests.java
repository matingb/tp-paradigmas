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
		 //lectorDeArchivos = new LectorArchivos();
	}

    @Test
    public void DadoArchivoConUnUsuario_AlLeer_DevuelveUnaListaConEseUsuario() throws IOException {
    	File inputFile = temporaryFolder.newFile("testfile.in");
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write("3\n5\n7\n");
        writer.close();

        //List<Usuario> usuarios = lectorDeArchivos.leerUsuarios();

        //assertEquals(1, usuarios.size());
    }
}

