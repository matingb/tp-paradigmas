package unlam.paradigmas.repositorios.usuarios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.Usuario;

public class ArchivoUsuarioRepository implements IUsuarioRepository {

	private Properties properties;
	private static ArchivoUsuarioRepository instance;

	private ArchivoUsuarioRepository(Properties properties) {
		this.properties = properties;
	}

	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Scanner scanner = null;

		try {
			String path = properties.getProperty("PathUsuarios");

			File file = new File(path);
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			seteaPipeYSaltoDeLineaComoDelimitador(scanner);

			while (scanner.hasNext()) {
				Usuario usuario = new Usuario();

				usuario.setPresupuesto(scanner.nextDouble());
				usuario.setTiempo(scanner.nextDouble());
				usuario.setActividadFavorita(TipoActividad.valueOf(scanner.next()));
				usuario.setNombre(scanner.next());

				usuarios.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return usuarios;
	}

	public static ArchivoUsuarioRepository getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static ArchivoUsuarioRepository init(Properties properties) {
		if (instance == null) {
			instance = new ArchivoUsuarioRepository(properties);
		}
		return instance;
	}

	private void seteaPipeYSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\\||\n");
	}
}
