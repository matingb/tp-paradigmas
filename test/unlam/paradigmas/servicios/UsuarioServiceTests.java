package unlam.paradigmas.servicios;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.mockito.Mockito;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.IUsuarioRepository;

public class UsuarioServiceTests {

	private static IUsuarioRepository usuarioRepository = Mockito.mock(IUsuarioRepository.class);

	private UsuarioService usuarioService;

	public UsuarioServiceTests() {
		usuarioService = UsuarioService.init(usuarioRepository);
	}

	@Test
	public void dados3Usuarios_AlObtenerUsuarios_DeberiaObtenerLosUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario("Valentin", 10.0, 20.0, "AVENTURA"));
		usuarios.add(usuario("Agustin", 30.0, 70.0, "DEGUSTACION"));
		usuarios.add(usuario("Tomas", 5.0, 10.0, "AVENTURA"));
		Mockito.when(usuarioRepository.getUsuarios()).thenReturn(usuarios);

		List<Usuario> usuariosActuales = usuarioService.getUsuarios();

		assertEquals(usuarios, usuariosActuales);
	}

	private Usuario usuario(String nombre, Double presupuesto, Double tiempo, String actividadFavorita) {
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPresupuesto(presupuesto);
		usuario.setTiempo(tiempo);
		usuario.setActividadFavorita(actividadFavorita);

		return usuario;
	}
}
