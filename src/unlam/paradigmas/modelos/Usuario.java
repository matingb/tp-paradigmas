package unlam.paradigmas.modelos;

public class Usuario {

	private Double presupuesto;
	private Double tiempo;
	private TipoActividad actividadFavorita;
	private String nombre;

	public Double getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}
	public Double getTiempo() {
		return tiempo;
	}
	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}
	public TipoActividad getActividadFavorita() {
		return actividadFavorita;
	}
	public void setActividadFavorita(TipoActividad actividadFavorita) {
		this.actividadFavorita = actividadFavorita;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
