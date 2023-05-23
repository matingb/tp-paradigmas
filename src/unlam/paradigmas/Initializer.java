package unlam.paradigmas;

import java.io.InputStream;
import java.util.Properties;

import unlam.paradigmas.repositorios.ArchivoRepository;
import unlam.paradigmas.repositorios.IUsuarioRepository;
import unlam.paradigmas.servicios.AtraccionService;
import unlam.paradigmas.servicios.UsuarioService;
import unlam.paradigmas.repositorios.IAtraccionRepository;

public class Initializer {
	public void initialize() {
		InputStream input = null;

		try {
			Properties properties = new Properties();
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			properties.load(input);

			IUsuarioRepository usuarioRepository = ArchivoRepository.init(properties);
			UsuarioService.init(usuarioRepository);

			IAtraccionRepository atraccionRepository = ArchivoRepository.getInstance();
			AtraccionService.init(atraccionRepository);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
