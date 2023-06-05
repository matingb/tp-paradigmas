package unlam.paradigmas;

import java.io.InputStream;
import java.util.Properties;

import unlam.paradigmas.factories.SesionFactory;
import unlam.paradigmas.repositorios.atracciones.ArchivoAtraccionRepository;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;
import unlam.paradigmas.repositorios.promociones.ArchivoPromocionRepository;
import unlam.paradigmas.repositorios.promociones.IPromocionRepository;
import unlam.paradigmas.repositorios.recibos.ArchivoRecibosRepository;
import unlam.paradigmas.repositorios.usuarios.ArchivoUsuarioRepository;

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
			
			Boleteria.init(atraccionRepository, promocionRepository, new ArchivoRecibosRepository(), new SesionFactory());
			
			Lector.init();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
