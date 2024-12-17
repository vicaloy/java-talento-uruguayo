package logica.excepciones;

public class ParticipanteNoExisteException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ParticipanteNoExisteException(String msg)
	{
		mensaje = msg;
	}
	public String getMensaje()
	{
		return mensaje;
	}
}