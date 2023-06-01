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

import unlam.paradigmas.modelos.TipoActividad;
import unlam.paradigmas.modelos.TipoPromocion;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionCombo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionMontoFijo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionPorcentual;
import unlam.paradigmas.repositorios.usuarioRepository.ArchivoUsuarioRepository;

public class ArchivoUsuarioRepositoryTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private ArchivoUsuarioRepository repository;

	private static Properties properties = Mockito.mock(Properties.class);

	public ArchivoUsuarioRepositoryTests() {
		repository = ArchivoUsuarioRepository.init(properties);
	}

	@Test
	public void DadoUnArchivoConUsuarios_AlLeer_ObtengoUnaListaDeUsuarios() throws IOException {
		dadoUnArchivoConContenido(
			"3|10|DEGUSTACION|MATI GARCIA\n" + 
			"7|11|AVENTURA|AGOS MOTTU\n" + 
			"12|12|AVENTURA|FEDE CASTRO\n");

		List<Usuario> usuarios = repository.getUsuarios();

		validarUsuario(3.0, 10.0, TipoActividad.DEGUSTACION, "MATI GARCIA", usuarios.get(0));
		validarUsuario(7.0, 11.0, TipoActividad.AVENTURA, "AGOS MOTTU", usuarios.get(1));
		validarUsuario(12.0, 12.0, TipoActividad.AVENTURA, "FEDE CASTRO", usuarios.get(2));

	}

	@Test
	public void DadoUnArchivoDeUsuariosVacio_AlLeer_NoObtengoUsuarios() throws IOException {
		dadoUnArchivoConContenido("");

		List<Usuario> usuarios = repository.getUsuarios();

		assertEquals(0, usuarios.size());
	}

	private void validarUsuario(Double presupuesto, Double tiempo, TipoActividad actividadFavorita, String nombre,
			Usuario usuario) {
		assertEquals(presupuesto, usuario.getPresupuesto());
		assertEquals(tiempo, usuario.getTiempo());
		assertEquals(actividadFavorita, usuario.getActividadFavorita());
		assertEquals(nombre, usuario.getNombre());
	}

	private void dadoUnArchivoConContenido(String contenido) throws IOException {
		File inputFile = temporaryFolder.newFile("archivoTemporal" + new Random().nextInt() +".in");
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		writer.write(contenido);
		writer.close();

		Mockito.when(properties.getProperty("PathUsuarios")).thenReturn(inputFile.getAbsolutePath());
	}
}
