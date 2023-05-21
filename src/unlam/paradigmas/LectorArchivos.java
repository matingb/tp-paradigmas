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
	public List<Usuario> leerUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario());
		
		return usuarios;
	}

	/*public List<Integer> leer() {
		List<Integer> datos = null;
		Scanner scanner = null;
		
		try {
			File file = new File("./casos/ejercicio1/in/" + nombre + ".in");
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			
			if(scanner.hasNextInt()) {
				int cant = scanner.nextInt();
				datos = new ArrayList<Integer>();
				for(int i = 0 ; i < cant; i++) {
					datos.add(scanner.nextInt());
				} 
			}
			else {
				return new ArrayList<Integer>();
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return datos;
	}
	
	public void guardar(List<Integer> datos) throws IOException {
		FileWriter file = null;
		PrintWriter printerWriter = null;
		
		try {
			file = new FileWriter("./casos/ejercicio1/out/" + nombre + ".out");
			printerWriter = new PrintWriter(file);
			
			for(Integer dato : datos) {
				printerWriter.print(dato + "\n");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		} finally {
			if(file != null) {
				file.close();
			}
		}
	}*/
}
