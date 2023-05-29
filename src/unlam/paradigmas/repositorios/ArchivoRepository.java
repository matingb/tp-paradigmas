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
			seteaSaltoDeLineaComoDelimitador(scanner);

			while (scanner.hasNext()) {

				String lineaPromocion = scanner.next();
				String[] campos = lineaPromocion.split("\\|");
				String lineaAtracciones = campos[3];
				String[] vectorAtracciones = lineaAtracciones.split("-");

							
				if (campos[0].equals("MONTO FIJO")) {
					PromocionMontoFijo promocion = new PromocionMontoFijo(TipoAtraccion.valueOf(campos[1]),
							 Double.parseDouble(campos[2]), vectorAtracciones, atraccionesVigentes);
					promociones.add(promocion);
				} else if (campos[0].equals("PORCENTUAL")) {
					PromocionPorcentual promocion = new PromocionPorcentual(TipoAtraccion.valueOf(campos[1]),
							Double.parseDouble(campos[2]), vectorAtracciones, atraccionesVigentes);
					promociones.add(promocion);
				} else {
					PromocionCombo promocion = new PromocionCombo(TipoAtraccion.valueOf(campos[1]),
							Integer.parseInt(campos[2]), vectorAtracciones, atraccionesVigentes);
					promociones.add(promocion);
				}

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

	private void seteaSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\n");
	}

}
