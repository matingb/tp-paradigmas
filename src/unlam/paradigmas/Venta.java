package unlam.paradigmas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Oferta;

public class Venta {
	private double precioTotal;
	private double tiempoTotal;
	private Usuario usuario;

	Venta(Usuario usuario) {
		this.usuario = usuario;
	}

	public void crearArchivo() {
		FileWriter archivo = null;
		PrintWriter escribirArch = null;

		try {
			archivo = new FileWriter("./salidas/" + usuario.getNombre() + ".out");// chequear si ruta OK
			escribirArch = new PrintWriter(archivo);

			// TODO escribir los datos del usuario
			escribirArch.println("					Resumen Itinerario				");
			escribirArch.println("-----------------------------------------------------------");
			escribirArch.println("Visitante: " + usuario.getNombre());
			escribirArch.println("Presupuesto: " + "$" + usuario.getPresupuesto());
			escribirArch.println("Tiempo Disponible: " + usuario.getTiempo() + "Hrs");
			escribirArch.println("Actividad Favorita: " + usuario.getActividadFavorita());
			escribirArch.println("-----------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// escribirOferta() va escribiendo todas las ofertas que acepta el usuario
	public void escribirOferta(Oferta oferta) {

		// TODO escribir en el archivo los datos de la oferta aceptada
		// invocar a actualizarTiempoyPrecio()

		FileWriter archivo = null;
		PrintWriter escribirArch = null;

		try {
			archivo = new FileWriter("./salidas/" + usuario.getNombre() + ".out", true);// chequear si ruta OK
			escribirArch = new PrintWriter(archivo);

			escribirArch.println(oferta);

			actualizarTiempoyPrecio(oferta);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void actualizarTiempoyPrecio(Oferta oferta) {
		tiempoTotal += oferta.getDuracion();
		precioTotal += oferta.getPrecio();
	}

	public void escribirTotales() {
		FileWriter archivo = null;
		PrintWriter printerWriter = null;

		try {
			archivo = new FileWriter("./salidas/" + usuario.getNombre() + ".out");
			printerWriter = new PrintWriter(archivo);
			printerWriter.println("Total a pagar: $" + precioTotal);
			printerWriter.println("Tiempo total: " + tiempoTotal + " hrs.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
