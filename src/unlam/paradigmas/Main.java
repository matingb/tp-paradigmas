package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.usuarioRepository.ArchivoUsuarioRepository;
import unlam.paradigmas.repositorios.usuarioRepository.IUsuarioRepository;

public class Main {

	public static void main(String[] args) {
		Initializer initialize = new Initializer();
		initialize.initialize();
		
		Boleteria boleteria = Boleteria.getInstance();
		
		IUsuarioRepository usuarioRepository = ArchivoUsuarioRepository.getInstance();
		List<Usuario> usuarios =  usuarioRepository.getUsuarios();
		
		for(Usuario usuario : usuarios) {			
			boleteria.atender(usuario);
		}
		
	}
}
