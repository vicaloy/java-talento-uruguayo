package logica.excepciones;

public class VotacionYaCerradaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public VotacionYaCerradaException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}

}
