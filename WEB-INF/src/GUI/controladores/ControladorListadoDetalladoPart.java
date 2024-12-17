package GUI.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import GUI.ventanas.ListadoDetalladoParticipante;
import logica.ICapaLogica;
import logica.excepciones.ParticipanteNoExisteException;
import valueObjects.VOParticipante;

public class ControladorListadoDetalladoPart {
	private ICapaLogica fachada;
	private ListadoDetalladoParticipante ventana;

	
	private final String errorNomArtistico = "Ingrese nombre artístico por favor. \n";
	private final String errorConex = "Se perdió la conexión con el servidor, intente nuevamente";

	public ControladorListadoDetalladoPart(ListadoDetalladoParticipante ventana) {
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

	public VOParticipante buscarParticipante(String nomArt) {

		VOParticipante part=null;
		String error = "";
		if (nomArt == null || nomArt.isEmpty()) {
			error = errorNomArtistico;
		}

		if (error.isEmpty()) {

			try {
				part=fachada.buscarParticipante(nomArt.toUpperCase());
				
			} catch (ParticipanteNoExisteException e) {
				ventana.mensajeError(e.getMensaje());
			} catch (RemoteException e) {
				ventana.mensajeError(errorConex);
			}

		} else {

			ventana.mensajeError(error);
		}
		
		return part;
	}

}
