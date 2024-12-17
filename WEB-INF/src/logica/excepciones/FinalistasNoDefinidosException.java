package logica.excepciones;

public class FinalistasNoDefinidosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public FinalistasNoDefinidosException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}

}
