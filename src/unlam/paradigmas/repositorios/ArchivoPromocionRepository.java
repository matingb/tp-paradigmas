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
import unlam.paradigmas.servicios.AtraccionService;

public class ArchivoPromocionRepository implements IPromocionRepository{
	
	private Properties properties;
	private AtraccionService atraccionService;
	
	private static ArchivoPromocionRepository instance;

	private ArchivoPromocionRepository(Properties properties, AtraccionService atraccionService) {
		this.properties = properties;
		this.atraccionService = atraccionService;
	}
	
	@Override
	public List<Promocion> getPromociones(List<Atraccion> atraccionesVigentes) {
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
				TipoAtraccion tipoDeAtracciones = TipoAtraccion.valueOf(scanner.next());
				Double valor = scanner.nextDouble();				
				String nombresDeAtraccionesIncluidas = scanner.next();

				List<Atraccion> atracciones = new ArrayList<Atraccion>();
				for (String nombreAtraccion : nombresDeAtraccionesIncluidas.split("-")) {
					Atraccion atraccion = atraccionService.getAtraccionByNombre(nombreAtraccion);
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

	public synchronized static ArchivoPromocionRepository init(Properties properties, AtraccionService atraccionService) {
		if (instance == null) {
			instance = new ArchivoPromocionRepository(properties, atraccionService);
		}
		return instance;
	}

	private void seteaPipeYSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\\||\n");
	}
}
