package unlam.paradigmas.modelos.ofertas;

import java.util.List;

import unlam.paradigmas.enums.TipoActividad;

public abstract class Oferta implements Comparable<Oferta>{
	
	private TipoActividad tipoActividad;
	
	public Oferta(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public abstract Double getPrecio();
	public abstract Double getDuracion();
	public abstract void descontarCupo();
	public abstract Boolean hayDisponibilidad();
	public abstract List<Atraccion> getAtraccionesIncluidas();
	
	public TipoActividad getTipoActividad() {
		return this.tipoActividad;
	}
	
	/*  Se ordena primero por precio, y en el caso de que sean iguales, ordena
	 * 	por duracion
	 */
	
	@Override
	public int compareTo(Oferta o) {
		
		int resultado = Double.compare(this.getPrecio(), o.getPrecio());
		
		if(resultado == 0)
			resultado = Double.compare(this.getDuracion(), o.getDuracion());
		
		return resultado;
	}

	
}
