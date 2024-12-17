package logica.excepciones;

public class DiccioMenosDeTresPartException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public DiccioMenosDeTresPartException(String msg)
	{
		mensaje = msg;
	}
	public String getMensaje()
	{
		return mensaje;
	}
}