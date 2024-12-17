package logica.excepciones;

public class PerformancesCerradasException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public PerformancesCerradasException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
