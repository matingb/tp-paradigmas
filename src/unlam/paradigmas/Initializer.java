package unlam.paradigmas;

import java.io.InputStream;
import java.util.Properties;

import unlam.paradigmas.repositorios.atraccionRepository.ArchivoAtraccionRepository;
import unlam.paradigmas.repositorios.atraccionRepository.IAtraccionRepository;
import unlam.paradigmas.repositorios.promocionRepository.ArchivoPromocionRepository;
import unlam.paradigmas.repositorios.promocionRepository.IPromocionRepository;
import unlam.paradigmas.repositorios.usuarioRepository.ArchivoUsuarioRepository;
import unlam.paradigmas.repositorios.usuarioRepository.IUsuarioRepository;

public class Initializer {
	public void initialize() {
		InputStream input = null;

		try {
			Properties properties = new Properties();
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			properties.load(input);

			ArchivoUsuarioRepository.init(properties);
			IAtraccionRepository atraccionRepository = ArchivoAtraccionRepository.init(properties);
			IPromocionRepository promocionRepository = ArchivoPromocionRepository.init(properties, atraccionRepository);
			
			Boleteria.init(atraccionRepository, promocionRepository, new SesionHandler());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
