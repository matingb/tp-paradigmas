package unlam.paradigmas.modelos;

import java.util.List;

public class Atraccion {

	private String nombre;
	private Double costo;
	private Double duracionHoras;
	private Integer cupo;
	private TipoAtraccion tipoAtraccion;

	public Atraccion(String nombre, Double costo, Double duracionHoras, Integer cupo, TipoAtraccion tipoAtraccion) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracionHoras = duracionHoras;
		this.cupo = cupo;
		this.tipoAtraccion = tipoAtraccion;
	}

	
	public String getNombre() {
		return nombre;
	}

	public Double getCosto() {
		return costo;
	}

	public Double getDuracionHoras() {
		return duracionHoras;
	}

	public Integer getCupo() {
		return cupo;
	}

	public TipoAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public static Atraccion buscarAtraccionPorNombre(List<Atraccion> atracciones, String nombre) {
		for (Atraccion a : atracciones) {
			if (a.getNombre().equals(nombre)) {
				return a;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
