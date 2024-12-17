package servlets;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ICapaLogica;
import logica.excepciones.FinalistasNoDefinidosException;
import logica.excepciones.ParticipanteNoExisteException;
import logica.excepciones.VotacionYaCerradaException;
import valueObjects.VOParticipanteFinalista;

public class VotoPublico extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICapaLogica fachada;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		boolean error = false;
	    String msgError = new String();
	    String datosVot = req.getParameter("datosVot");
		try {
			String servidor = super.getInitParameter("ipServidor");
			String puerto = super.getInitParameter("puertoServidor");
			String ruta = "//" + servidor + ":" + puerto + "/fachada";

			fachada = (ICapaLogica) Naming.lookup(ruta);

		} catch (IOException | NotBoundException e) 
		{
			error = true;
			msgError = "Error al conectar con el servidor";
		}
		
		msgError = datosVot;
		
	    if(!error) {
	    	
	    try 
	    {
			fachada.votarFinalista(datosVot);
		} catch (VotacionYaCerradaException | FinalistasNoDefinidosException | ParticipanteNoExisteException e) 
	    {
			error = true;
			msgError = "La votacion ya se cerro!!";
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
