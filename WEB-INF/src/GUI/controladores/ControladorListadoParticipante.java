package GUI.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import GUI.ventanas.ListadoParticipante;
import logica.ICapaLogica;
import logica.excepciones.DiccioVacioException;
import valueObjects.VOParticipanteListado;

public class ControladorListadoParticipante {
	private ICapaLogica fachada;
	private ListadoParticipante ventana;

	private final String errorConex = "Se perdió la conexión con el servidor, intente nuevamente";

	public ControladorListadoParticipante(ListadoParticipante ventana) {
		this.ventana = ventana;
		try {
			Properties p = new Properties();
			String nomArch = "config/archivo.properties";
			p.load(new FileInputStream(nomArch));
			String servidor = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + servidor + ":" + puerto + "/fachada";

			fachada = (ICapaLogica) Naming.lookup(ruta);
			ventana.setVisible(true);
		} catch (IOException | NotBoundException e) {

			ventana.mensajeError(errorConex);
		}

	}

	public ArrayList<VOParticipanteListado> listarParticipantes() {
		ArrayList<VOParticipanteListado> part = null;

		try {
			System.out.println("CONTROLADOR, antes de mandar a la fachada");
			part = fachada.listarParticipantes();
			System.out.println("CONTROLADOR, luego de mandar a la fachada");

		} catch (DiccioVacioException e) {
			ventana.mensajeError(e.getMensaje());
		} catch (RemoteException e) {
			ventana.mensajeError(errorConex);
		}

		return part;

	}
}
