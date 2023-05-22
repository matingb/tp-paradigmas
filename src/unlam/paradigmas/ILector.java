package unlam.paradigmas;

import java.util.List;

public interface ILector {
	public List<Usuario> leerUsuarios(String path);

	public List<Atraccion> leerAtracciones(String path);
}
