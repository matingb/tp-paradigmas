package unlam.paradigmas.repositorios.recibos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.Recibo;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class ArchivoRecibosRepositoryTests {
    private ArchivoRecibosRepository repository;

    private static Properties properties = Mockito.mock(Properties.class);

    public ArchivoRecibosRepositoryTests() {
        repository = ArchivoRecibosRepository.init(properties);
    }
	
	@Test
	public void dadoUnUsuario_DeberiaGenerarUnArchivoDeSalida () throws IOException {
		Mockito.when(properties.getProperty("PathRecibos")).thenReturn("./test/unlam/paradigmas/repositorios/recibos/");
		
		Atraccion atraccionIncluida = new Atraccion("atraccionPrueba", 50.0, 30.0, 1, TipoActividad.AVENTURA);
		Usuario usuario = new Usuario("Nombre", 17.0, 11.0, TipoActividad.AVENTURA);
		Recibo recibo = new Recibo(usuario);
		
		recibo.agregarVenta(atraccionIncluida);
		repository.escribir(recibo);
		
        byte[] archivoEsperado = Files.readAllBytes(Paths.get("./test/unlam/paradigmas/repositorios/recibos/archivoSalidaEsperado.out"));
        byte[] archivoObtenido = Files.readAllBytes(Paths.get("./test/unlam/paradigmas/repositorios/recibos/Nombre.out"));

        Assert.assertArrayEquals(archivoEsperado, archivoObtenido);
	}
}