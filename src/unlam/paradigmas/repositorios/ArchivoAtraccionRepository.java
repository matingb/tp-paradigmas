package unlam.paradigmas.repositorios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.PromocionCombo;
import unlam.paradigmas.modelos.PromocionMontoFijo;
import unlam.paradigmas.modelos.PromocionPorcentual;
import unlam.paradigmas.modelos.TipoAtraccion;
import unlam.paradigmas.modelos.TipoPromocion;
import unlam.paradigmas.modelos.Usuario;

public class ArchivoAtraccionRepository implements IAtraccionRepository {

	private Properties properties;
	private static ArchivoAtraccionRepository instance;

	private ArchivoAtraccionRepository(Properties properties) {
		this.properties = properties;
	}

	@Override
	public List<Atraccion> getAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Scanner scanner = null;

		try {
			String path = properties.getProperty("PathAtracciones");
			File file = new File(path);
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			seteaPipeYSaltoDeLineaComoDelimitador(scanner);

			while (scanner.hasNext()) {
				String nombre = scanner.next();
				Double costo = scanner.nextDouble();
				Double duracionHoras = scanner.nextDouble();
				Integer cupo = scanner.nextInt();
				TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(scanner.next());

				Atraccion atraccion = new Atraccion(nombre, costo, duracionHoras, cupo, tipoAtraccion);
				atracciones.add(atraccion);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return atracciones;

	}

	public static ArchivoAtraccionRepository getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static ArchivoAtraccionRepository init(Properties properties) {
		if (instance == null) {
			instance = new ArchivoAtraccionRepository(properties);
		}
		return instance;
	}

	private void seteaPipeYSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\\||\n");
	}
}
