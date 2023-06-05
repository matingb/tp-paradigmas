package unlam.paradigmas;

import java.util.List;

import unlam.paradigmas.modelos.Usuario;
import unlam.paradigmas.repositorios.usuarios.ArchivoUsuarioRepository;
import unlam.paradigmas.repositorios.usuarios.IUsuarioRepository;

public class Main {

	public static void main(String[] args) {
		Initializer initialize = new Initializer();
		initialize.initialize();
		
		Boleteria boleteria = Boleteria.getInstance();
		
		IUsuarioRepository usuarioRepository = ArchivoUsuarioRepository.getInstance();
		List<Usuario> usuarios =  usuarioRepository.getUsuarios();
		
		for(Usuario usuario : usuarios) {
			System.out.println("Buenos días " + usuario.getNombre());
			boleteria.atender(usuario);
		}	
		
		System.out.println("Programa Finalizado");
	}
}
