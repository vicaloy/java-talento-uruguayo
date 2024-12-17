package servlets;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ICapaLogica;
import logica.excepciones.FinalistasNoDefinidosException;
import valueObjects.VOParticipanteFinalista;

public class ListarFinalistas extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICapaLogica fachada;

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		boolean error = false;
	    String msgError = new String();
	    
		try {
			String servidor = super.getInitParameter("ipServidor");
			String puerto = super.getInitParameter("puertoServidor");
			String ruta = "//" + servidor + ":" + puerto + "/fachada";

			fachada = (ICapaLogica) Naming.lookup(ruta);

		} catch (IOException | NotBoundException e) {
			error = true;
			msgError = "Error al conectar con el servidor";
		}
		
		ArrayList<VOParticipanteFinalista> parti = new ArrayList<VOParticipanteFinalista>();
		if(!error) {

		try 
		{
			parti = fachada.listarFinalistasYGanador() ;
		} catch (FinalistasNoDefinidosException e) {
			error = true;
			msgError = "Aun no se han definido los finalistas";
		}
		
		HttpSession session = req.getSession();
		
		if (!error)
	    {	
		    VOParticipanteFinalista datosFin1 = new VOParticipanteFinalista(parti.get(0).getNomArtistico(),parti.get(0).getEdad(),parti.get(0).getCantVotos(),parti.get(0).getFoto() );
		    synchronized (session)
		    {
		        session.setAttribute("datosFin1",datosFin1);    
		    }
		    VOParticipanteFinalista datosFin2 = new VOParticipanteFinalista(parti.get(1).getNomArtistico(),parti.get(1).getEdad(),parti.get(1).getCantVotos(),parti.get(1).getFoto() );
		    synchronized (session)
		    {
		        session.setAttribute("datosFin2",datosFin2);    
		    }
		    VOParticipanteFinalista datosFin3 = new VOParticipanteFinalista(parti.get(2).getNomArtistico(),parti.get(2).getEdad(),parti.get(2).getCantVotos(),parti.get(2).getFoto() );
		    synchronized (session)
		    {
		        session.setAttribute("datosFin3",datosFin3);    
		    }
		    if(parti.get(3) != null)
		{
			VOParticipanteFinalista ganador = new VOParticipanteFinalista(parti.get(3).getNomArtistico(),parti.get(3).getEdad(),parti.get(3).getCantVotos(),parti.get(3).getFoto() );
		    synchronized (session)
		    {
		        session.setAttribute("ganador",ganador);    
		    }
		}
		else
		{
			synchronized (session)
		    {
		        session.setAttribute("ganador",null);    
		    }
		}
	    }   
		
		
		}
	    req.setAttribute("msgError", msgError);
		RequestDispatcher rd;
		if (error == false)
			rd = req.getRequestDispatcher("ListadoFinalistas.jsp");
		else
			rd = req.getRequestDispatcher("Error.jsp");
		rd.forward(req, resp);
	}
}
