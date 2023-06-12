package unlam.paradigmas.repositorios.promociones;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import unlam.paradigmas.enums.TipoActividad;
import unlam.paradigmas.enums.TipoPromocion;
import unlam.paradigmas.modelos.ofertas.Atraccion;
import unlam.paradigmas.modelos.ofertas.promociones.Promocion;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionCombo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionMontoFijo;
import unlam.paradigmas.modelos.ofertas.promociones.PromocionPorcentual;
import unlam.paradigmas.repositorios.atracciones.IAtraccionRepository;

public class ArchivoPromocionRepository implements IPromocionRepository{
	
	private Properties properties;
	private IAtraccionRepository atraccionRepository;
	
	private static ArchivoPromocionRepository instance;

	private ArchivoPromocionRepository(Properties properties, IAtraccionRepository atraccionRepository) {
		this.properties = properties;
		this.atraccionRepository = atraccionRepository;
	}
	
	@Override
	public List<Promocion> getPromociones() {
		List<Promocion> promociones = new ArrayList<Promocion>();
		Scanner scanner = null;

		try {
			String path = properties.getProperty("PathPromociones");

			File file = new File(path);
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			seteaPipeYSaltoDeLineaComoDelimitador(scanner);

			while (scanner.hasNext()) {

				TipoPromocion tipoDePromocion = TipoPromocion.valueOf(scanner.next());
				TipoActividad tipoDeAtracciones = TipoActividad.valueOf(scanner.next());
				Double valor = scanner.nextDouble();				
				String nombresDeAtraccionesIncluidas = scanner.next();

				List<Atraccion> atracciones = new ArrayList<Atraccion>();
				for (String nombreAtraccion : nombresDeAtraccionesIncluidas.split("-")) {
					Atraccion atraccion = atraccionRepository.getAtraccionByNombre(nombreAtraccion);
					atracciones.add(atraccion);
				}
				
				Promocion promocion = null;
				
				if (TipoPromocion.MONTO_FIJO.equals(tipoDePromocion)) {
					promocion = new PromocionMontoFijo(tipoDeAtracciones, valor, atracciones);
				} else if (TipoPromocion.PORCENTUAL.equals(tipoDePromocion)) {
					promocion = new PromocionPorcentual(tipoDeAtracciones, valor, atracciones);
				} else if (TipoPromocion.COMBO.equals(tipoDePromocion)){
					promocion = new PromocionCombo(tipoDeAtracciones, valor.intValue(), atracciones);
				}
				
				promociones.add(promocion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return promociones;
	}

	public static ArchivoPromocionRepository getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static ArchivoPromocionRepository init(Properties properties, IAtraccionRepository atraccionRepository) {
		if (instance == null) {
			instance = new ArchivoPromocionRepository(properties, atraccionRepository);
		}
		return instance;
	}

	private void seteaPipeYSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\\||\n|\r\n");
	}
}
