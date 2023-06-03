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
	
	Venta(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void crearArchivo() {
		File archivo = new File("./salidas/"+usuario.getNombre()+".out");//chequear si ruta OK
		
		//TODO escribir los datos del usuario
	}
	
	// escribirOferta() va escribiendo todas las ofertas que acepta el usuario
	public void escribirOferta(Oferta oferta) { 
		
		//TODO escribir en el archivo los datos de la oferta aceptada
		//invocar a actualizarTiempoyPrecio()
	}
	
	
	private void actualizarTiempoyPrecio(Oferta oferta) {
		tiempoTotal+=oferta.getDuracion();
		precioTotal+=oferta.getPrecio();
	}
	
	
	public void escribirTotales() {
		FileWriter archivo = null;
		PrintWriter printerWriter = null;
		
		try {
			archivo = new FileWriter("./salidas/"+usuario.getNombre()+".out");
			printerWriter = new PrintWriter(archivo);
			printerWriter.println("Total a pagar: $"+precioTotal);
			printerWriter.println("Tiempo total: "+tiempoTotal+" hrs.");
		}
		catch (Exception e) {
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
