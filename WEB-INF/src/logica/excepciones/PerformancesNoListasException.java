package logica.excepciones;

public class PerformancesNoListasException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public PerformancesNoListasException(String msg)
	{
		mensaje = msg;
	}
	public String getMensaje()
	{
		return mensaje;
	}
}