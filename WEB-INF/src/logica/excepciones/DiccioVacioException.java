package logica.excepciones;

public class DiccioVacioException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public DiccioVacioException(String msg)
	{
		mensaje = msg;
	}
	public String getMensaje()
	{
		return mensaje;
	}
}