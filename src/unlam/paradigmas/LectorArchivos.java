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
	public List<Usuario> leerUsuarios(String pathDelArhivo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Scanner scanner = null;
		
		try {
			File file = new File(pathDelArhivo);
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			
			/*String string = scanner.nextLine();
			String[] parts = string.split();*/
			
			Usuario usuario = new Usuario();
			Double presupuesto = scanner.nextDouble();
			Double tiempo = scanner.nextDouble();
			String actividadFavorita = scanner.next();
			String nombre = scanner.next();
			
			usuario.presupuesto = presupuesto;
			usuario.tiempo = tiempo;
			usuario.actividadFavorita = actividadFavorita;
			usuario.nombre = nombre;
			
			usuarios.add(usuario);
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return usuarios;
	}
}
