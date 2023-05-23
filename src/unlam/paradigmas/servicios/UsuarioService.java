package unlam.paradigmas.servicios;

import java.util.List;
import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.IUsuarioRepository;

public class UsuarioService implements IUsuarioService{
	
	public static UsuarioService instance; 
	
	private IUsuarioRepository usuarioRepository;

	private UsuarioService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarioRepository.getUsuarios();
	}
	
	public static UsuarioService getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static UsuarioService init(IUsuarioRepository usuarioRepository) {
		if (instance == null) {
			instance = new UsuarioService(usuarioRepository);
		}
		return instance;
	}
}
