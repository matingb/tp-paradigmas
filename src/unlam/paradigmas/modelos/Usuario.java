package unlam.paradigmas.modelos;

import unlam.paradigmas.enums.TipoActividad;

public class Usuario {

	private Double presupuesto;
	private Double tiempo;
	private TipoActividad actividadFavorita;
	private String nombre;
	
	public Usuario() {}
	public Usuario(String nombre, Double presupuesto, Double tiempo, TipoActividad actividad) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.actividadFavorita = actividad;
	}
	
	
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
	
	public void pagarBoleteria (double dineroPagar) {
		this.presupuesto -= dineroPagar;
	}
	
	public void reducirTiempo (double dineroReducir){
		this.tiempo -= dineroReducir;
	}

}
