package unlam.paradigmas;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LectorArchivos implements ILector {

	@Override
	public List<Usuario> leerUsuarios(String path) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Scanner scanner = null;

		try {
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
		}
		finally {
			scanner.close();
		}

		return usuarios;
	}

	@Override
	public List<Atraccion> leerAtracciones(String path){
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Scanner scanner = null;

		try {
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
				atraccion.setTipoAtraccion(scanner.next());

				atracciones.add(atraccion);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}

		return atracciones;

	}
	
	private void seteaPipeYSaltoDeLineaComoDelimitador(Scanner scanner) {
		scanner.useDelimiter("\\||\n");
	}
	
	
}
