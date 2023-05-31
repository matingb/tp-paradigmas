package unlam.paradigmas;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;
import unlam.paradigmas.modelos.TipoActividad;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.ArchivoAtraccionRepository;
import unlam.paradigmas.repositorios.ArchivoPromocionRepository;
import unlam.paradigmas.repositorios.ArchivoUsuarioRepository;
import unlam.paradigmas.repositorios.IAtraccionRepository;
import unlam.paradigmas.repositorios.IPromocionRepository;
import unlam.paradigmas.repositorios.IUsuarioRepository;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Initializer initialize = new Initializer();
		initialize.initialize();

		IAtraccionRepository atraccionRepository = ArchivoAtraccionRepository.getInstance();
		IPromocionRepository promocionRepository = ArchivoPromocionRepository.getInstance();
		
		Usuario usuario = new Usuario();
		usuario.setPresupuesto(50.0);
		usuario.setTiempo(20.0);
		usuario.setActividadFavorita(TipoActividad.AVENTURA);
		
		Boleteria boleteria = new Boleteria(atraccionRepository, promocionRepository, new SesionHandler());
		
		boleteria.atender(usuario);
		
		/*
		 * Cargar Información - Usuarios - Atracciones - Paquetes Hace falta hacer la
		 * distinción que plantea el enunciado? No especifica ningun tipo de manejo Se
		 * podría únicamente cargar una lista atracciones con un precio final Por ej
		 * |valor|atraccion1|atraccion2
		 */

		/*
		 * Procesar la info Me imagino una clase como Boleteria la cual tiene
		 * conocimiento de las atracciones, especialmente del cupo. Y es el encargado de
		 * iterar los usuarios.
		 * 
		 * Por otro lado por cada usuario se genera una Sesion, la cual tendría en el
		 * encargado de las interacciones con el usuario, mostrando y priorizando lo que
		 * el usuario le pida. Cargar todo lo seleccionado y descontar cupo de lo
		 * elegido
		 */

		/*
		 * Escribir archivo con los resultados.
		 */
	}

}
