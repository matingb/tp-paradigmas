package unlam.paradigmas.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Atraccion extends Oferta{

	private String nombre;
	private Double precio;
	private Double duracionHoras;
	private Integer cupo;
	private TipoActividad tipoActividad;

	public Atraccion(String nombre, Double precio, Double duracionHoras, Integer cupo, TipoActividad tipoActividad) {
		this.nombre = nombre;
		this.precio = precio;
		this.duracionHoras = duracionHoras;
		this.cupo = cupo;
		this.tipoActividad = tipoActividad;
	}
	
	public Atraccion() {}

	public String getNombre() {
		return nombre;
	}

	@Override
	public Double getPrecio() {
		return precio;
	}
	
	public void setCosto(Double costo) {
		this.precio = costo;
	}

	@Override
	public Double getDuracion() {
		return duracionHoras;
	}

	public Integer getCupo() {
		return cupo;
	}

	@Override
	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	@Override
	public String toString() {
		return nombre;
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
		return Objects.equals(precio, other.precio) && Objects.equals(cupo, other.cupo)
				&& Objects.equals(duracionHoras, other.duracionHoras) && Objects.equals(nombre, other.nombre)
				&& tipoActividad == other.tipoActividad;
	}

	@Override
	public void descontarCupo() {
		this.cupo--;
		
	}

	@Override
	public Boolean hayDisponibilidad() {
		return this.cupo >= 0;
	}
	
	@Override
	public List<Atraccion> getAtraccionesIncluidas() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(this);
		return atracciones;
	}
}
