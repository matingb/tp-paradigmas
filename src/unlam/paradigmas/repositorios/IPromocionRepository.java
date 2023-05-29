package unlam.paradigmas.repositorios;

import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.modelos.Promocion;

public interface IPromocionRepository {
	public List<Promocion> getPromociones(List<Atraccion> atraccionesVigentes);
}
