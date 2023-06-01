package unlam.paradigmas.repositorios.atracciones;

import java.util.List;

import unlam.paradigmas.modelos.ofertas.Atraccion;

public interface IAtraccionRepository {
	public List<Atraccion> getAtracciones();

	public Atraccion getAtraccionByNombre(String nombreAtraccion);
}
