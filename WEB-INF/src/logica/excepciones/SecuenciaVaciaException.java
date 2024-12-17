package logica.excepciones;

public class SecuenciaVaciaException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public SecuenciaVaciaException(String msg)
	{
		mensaje = msg;
	}
	public String getMensaje()
	{
		return mensaje;
	}
}