package unlam.paradigmas.modelos;

public class Atraccion {
		
	private String nombre;
	private Double costo;
	private Double duracionHoras;
	private Integer cupo;
	private TipoAtraccion tipoAtraccion;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public void setDuracionHoras(Double duracionHoras) {
		this.duracionHoras = duracionHoras;
	}
	public Integer getCupo() {
		return cupo;
	}
	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}
	public TipoAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}
	public void setTipoAtraccion(TipoAtraccion tipoAtraccion) {
		this.tipoAtraccion = tipoAtraccion;
	}
}
