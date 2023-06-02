package unlam.paradigmas.modelos.ofertas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import unlam.paradigmas.enums.TipoActividad;

public class Atraccion extends Oferta{

	private String nombre;
	private Double precio;
	private Double duracionHoras;
	private Integer cupo;

	public Atraccion(String nombre, Double precio, Double duracionHoras, Integer cupo, TipoActividad tipoActividad) {
		super(tipoActividad);
		this.nombre = nombre;
		this.precio = precio;
		this.duracionHoras = duracionHoras;
		this.cupo = cupo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Integer getCupo() {
		return cupo;
	}

	
	@Override
	public Double getPrecio() {
		return precio;
	}
	
	@Override
	public Double getDuracion() {
		return duracionHoras;
	}

	@Override
	public void descontarCupo() {
		this.cupo--;
	}

	@Override
	public Boolean hayDisponibilidad() {
		return this.cupo > 0;
	}
	
	@Override
	public List<Atraccion> getAtraccionesIncluidas() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(this);
		return atracciones;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Objects.equals(cupo, other.cupo) && Objects.equals(duracionHoras, other.duracionHoras)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio)
				&& Objects.equals(this.getTipoActividad(), other.getTipoActividad());
	}

	@Override
	public String toString() {
		return "Atraccion" 
				+"\n-Nombre=" + nombre
				+"\n-Precio=" + precio
				+"\n-Duracion en horas=" + duracionHoras
				+"\n";
	}
	
	
}
