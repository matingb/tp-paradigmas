package unlam.paradigmas;

import unlam.paradigmas.servicios.UsuarioService;
import unlam.paradigmas.servicios.IUsuarioService;
import unlam.paradigmas.modelos.Usuario;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Initializer initialize = new Initializer();
		initialize.initialize();
		
		IUsuarioService usuarioService = UsuarioService.getInstance();
		List<Usuario> usuarios = usuarioService.getUsuarios();
		
		for(Usuario usuario : usuarios) {
			System.out.println(usuario.getNombre());
		}
		/*
		 	Cargar Información
			- Usuarios
			- Atracciones
			- Paquetes
				Hace falta hacer la distinción que plantea el enunciado?
				No especifica ningun tipo de manejo
				Se podría únicamente cargar una lista atracciones con un precio final
				Por ej |valor|atraccion1|atraccion2
		*/

		/*
			Procesar la info
				Me imagino una clase como Boleteria la cual tiene conocimiento
				de las atracciones, especialmente del cupo. Y es el encargado de
				iterar los usuarios.

				Por otro lado por cada usuario se genera una Sesion, la cual tendría
				en el encargado de las interacciones con el usuario, mostrando y priorizando
				lo que el usuario le pida. Cargar todo lo seleccionado y descontar cupo de lo
				elegido
		*/

		/*
			Escribir archivo con los resultados.
		*/
	}

}
