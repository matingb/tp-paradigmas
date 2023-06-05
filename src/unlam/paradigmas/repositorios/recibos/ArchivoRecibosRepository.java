package unlam.paradigmas.repositorios.recibos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import unlam.paradigmas.modelos.Recibo;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.modelos.ofertas.Oferta;

public class ArchivoRecibosRepository implements IReciboRepository {

	@Override
	public void escribir(Recibo recibo) {
		FileWriter archivo = null;
		PrintWriter writer = null;
		try {
			Usuario usuario = recibo.getUsuario();
			String usuarioSinSaltoDeLinea = usuario.getNombre().split("\n")[0];
			archivo = new FileWriter("./salidas/" + usuarioSinSaltoDeLinea + ".out");
			writer = new PrintWriter(archivo);
			escribirEncabezado(writer, usuario);
			
			for (Oferta venta : recibo.getOfertasVendidas()) {				
				writer.println(venta);
			}

			writer.println("Costo Total: " + "$" + recibo.getPrecioTotal());
			writer.println("Tiempo Total: " + recibo.getTiempoTotal() + "Hrs");
			
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

	private void escribirEncabezado(PrintWriter writer, Usuario usuario) {
		writer.println("					Resumen Itinerario				");
		writer.println("-----------------------------------------------------------");
		writer.println("Visitante: " + usuario.getNombre());
		writer.println("Presupuesto: " + "$" + usuario.getPresupuesto());
		writer.println("Tiempo Disponible: " + usuario.getTiempo() + "Hrs");
		writer.println("Actividad Favorita: " + usuario.getActividadFavorita());
		writer.println("-----------------------------------------------------------");
	}
}
