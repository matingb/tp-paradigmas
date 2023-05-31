package unlam.paradigmas.modelos;

import java.util.Objects;

public class Atraccion {

	private String nombre;
	private Double costo;
	private Double duracionHoras;
	private Integer cupo;
	private TipoActividad tipoActividad;

	public Atraccion(String nombre, Double costo, Double duracionHoras, Integer cupo, TipoActividad tipoActividad) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracionHoras = duracionHoras;
		this.cupo = cupo;
		this.tipoActividad = tipoActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getCosto() {
		return costo;
	}
	
	public void setCosto(Double costo) {
		this.costo = costo;
	}


	public Double getDuracionHoras() {
		return duracionHoras;
	}

	public Integer getCupo() {
		return cupo;
	}

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
		return Objects.equals(costo, other.costo) && Objects.equals(cupo, other.cupo)
				&& Objects.equals(duracionHoras, other.duracionHoras) && Objects.equals(nombre, other.nombre)
				&& tipoActividad == other.tipoActividad;
	}
}
