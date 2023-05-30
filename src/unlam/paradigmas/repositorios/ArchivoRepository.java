package unlam.paradigmas.repositorios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

//import javax.swing.plaf.synth.SynthOptionPaneUI;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.PromocionCombo;
import unlam.paradigmas.modelos.PromocionMontoFijo;
import unlam.paradigmas.modelos.PromocionPorcentual;
import unlam.paradigmas.modelos.TipoAtraccion;
import unlam.paradigmas.modelos.TipoPromocion;
import unlam.paradigmas.modelos.Usuario;

public class ArchivoRepository implements IUsuarioRepository, IAtraccionRepository, IPromocionRepository {

	private Properties properties;
	private static ArchivoRepository instance;

	private ArchivoRepository(Properties properties) {
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
				usuario.setActividadFavorita(scanner.next());
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
				Atraccion atraccion = new Atraccion();

				atraccion.setNombre(scanner.next());
				atraccion.setCosto(scanner.nextDouble());
				atraccion.setDuracionHoras(scanner.nextDouble());
				atraccion.setCupo(scanner.nextInt());
				atraccion.setTipoAtraccion(TipoAtraccion.valueOf(scanner.next()));

				atracciones.add(atraccion);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return atracciones;

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
				String atracciones = scanner.next();
				String[] vectorAtracciones = atracciones.split("-");
				
				Promocion promocion = null;
				
				if (TipoPromocion.MONTO_FIJO.equals(tipoDePromocion)) {
					promocion = new PromocionMontoFijo(tipoDeAtracciones, valor, vectorAtracciones, atraccionesVigentes);
				} else if (TipoPromocion.PORCENTUAL.equals(tipoDePromocion)) {
					promocion = new PromocionPorcentual(tipoDeAtracciones, valor, vectorAtracciones, atraccionesVigentes);
				} else if (TipoPromocion.COMBO.equals(tipoDePromocion)){
					promocion = new PromocionCombo(tipoDeAtracciones, valor.intValue(), vectorAtracciones, atraccionesVigentes);
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

	public static ArchivoRepository getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static ArchivoRepository init(Properties properties) {
		if (instance == null) {
			instance = new ArchivoRepository(properties);
		}
		return instance;
	}

	private void seteaPipeYSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\\||\n");
	}
}
