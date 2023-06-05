package unlam.paradigmas;

import java.util.Scanner;

public class Lector {
	
	private static Lector instance;
	private Scanner scanner = new Scanner(System.in);
	
	private Lector() {}
	
	public String leer() {
		return scanner.nextLine();
	}
	
	public static Lector getInstance() {
		if (instance == null) {
			throw new AssertionError("Debe llamarse primero al init");
		}

		return instance;
	}

	public synchronized static Lector init() {
		if (instance == null) {
			instance = new Lector();
		}
		return instance;
	}
}
