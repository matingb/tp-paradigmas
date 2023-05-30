package unlam.paradigmas;

import java.io.InputStream;
import java.util.Properties;

import unlam.paradigmas.repositorios.ArchivoAtraccionRepository;
import unlam.paradigmas.repositorios.ArchivoPromocionRepository;
import unlam.paradigmas.repositorios.ArchivoUsuarioRepository;
import unlam.paradigmas.repositorios.IUsuarioRepository;
import unlam.paradigmas.servicios.AtraccionService;
import unlam.paradigmas.servicios.PromocionService;
import unlam.paradigmas.servicios.UsuarioService;
import unlam.paradigmas.repositorios.IAtraccionRepository;
import unlam.paradigmas.repositorios.IPromocionRepository;

public class Initializer {
	public void initialize() {
		InputStream input = null;

		try {
			Properties properties = new Properties();
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			properties.load(input);

			IUsuarioRepository usuarioRepository = ArchivoUsuarioRepository.init(properties);
			UsuarioService.init(usuarioRepository);

			IAtraccionRepository atraccionRepository = ArchivoAtraccionRepository.init(properties);
			AtraccionService atraccionService = AtraccionService.init(atraccionRepository);
			
			IPromocionRepository promocionRepository = ArchivoPromocionRepository.init(properties, atraccionService);
			PromocionService.init(promocionRepository);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
