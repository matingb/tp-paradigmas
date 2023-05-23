package unlam.paradigmas.servicios;

import java.util.List;

import unlam.paradigmas.modelos.Atraccion;
import unlam.paradigmas.repositorios.IAtraccionRepository;

public class AtraccionService implements IAtraccionService {
	public static AtraccionService instance;

	private IAtraccionRepository atraccionRepository;

	private AtraccionService(IAtraccionRepository atraccionRepository) {
		this.atraccionRepository = atraccionRepository;
	}

	public List<Atraccion> getAtracciones() {
		return atraccionRepository.getAtracciones();
	}

	public static AtraccionService getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static AtraccionService init(IAtraccionRepository atraccionRepository) {
		if (instance == null) {
			instance = new AtraccionService(atraccionRepository);
		}
		return instance;
	}
}
