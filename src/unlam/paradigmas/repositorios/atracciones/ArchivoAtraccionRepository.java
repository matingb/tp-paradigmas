package unlam.paradigmas.repositorios.atracciones;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.modelos.ofertas.Atraccion;

public class ArchivoAtraccionRepository implements IAtraccionRepository {

	private Properties properties;
	private static ArchivoAtraccionRepository instance;
	private List<Atraccion> atracciones = null;

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
				TipoActividad tipoActividad = TipoActividad.valueOf(scanner.next());

				Atraccion atraccion = new Atraccion(nombre, costo, duracionHoras, cupo, tipoActividad);
				atracciones.add(atraccion);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		this.atracciones = atracciones;
		return atracciones;
	}
	
	@Override
	public Atraccion getAtraccionByNombre(String nombreAtraccion) {
		
		if(atracciones == null) {
			this.getAtracciones();
		}
		
		return this.atracciones.stream()
	    .filter(atraccion -> atraccion.getNombre().equals(nombreAtraccion)).findFirst()
	    .orElse(null);
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
		scanner.useDelimiter("\\||\n|\r\n");
	}
}
