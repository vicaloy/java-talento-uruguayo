package GUI.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import GUI.ventanas.IngresarPerformance;
import logica.ICapaLogica;
import logica.excepciones.ParticipanteNoExisteException;
import logica.excepciones.PerformancesCerradasException;
import logica.excepciones.SecuenciaCompletaException;
import valueObjects.VOPerformance;

public class ControladorIngresarPerformance {
	private ICapaLogica fachada;
	private IngresarPerformance ventana;

	private final String errorNomArtistico = "Ingrese nombre artístico por favor. \n";
	private final String errorDescripcion = "Ingrese una descripción por favor. \n";
	private final String errorPtosUno = "Ingrese puntaje uno por favor. \n";
	private final String errorPtosDos = "Ingrese puntaje dos por favor. \n";
	private final String errorPtosTres = "Ingrese puntaje tres por favor. \n";
	private final String exito = "Se ingresó la performance exitosamente";
	private final String errorConex = "Se perdió la conexión con el servidor, intente nuevamente";

	public ControladorIngresarPerformance(IngresarPerformance ventana) {
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
			
			ventana.mensajeError(errorConex, false);
		}

	}

	public void ingresarPerformance(String nomArt, String descripcion, int puntajeUno, int puntajeDos,
			int puntajeTres) {
		String error = "";
		if (nomArt == null || nomArt.isEmpty()) {
			error = errorNomArtistico;
		}

		if (descripcion == null || descripcion.isEmpty()) {
			error += errorDescripcion;
		}

		if (error.isEmpty()) {
			
			VOPerformance perf = new VOPerformance(0, descripcion.toUpperCase(), puntajeUno, puntajeDos, puntajeTres);

			try {
				System.out.println("CONTROLADOR, antes de mandar a la fachada");
				fachada.ingresarPerformance(nomArt.toUpperCase(), perf);
				System.out.println("CONTROLADOR, luego de mandar a la fachada");

				ventana.mensajeError(exito, true);

			} catch (SecuenciaCompletaException e) {
				ventana.mensajeError(e.getMensaje(), false);
			} catch (ParticipanteNoExisteException e) {
				ventana.mensajeError(e.getMensaje(), false);
			} catch (PerformancesCerradasException e) {
				ventana.mensajeError(e.getMensaje(), false);
			} catch (RemoteException e) {
				ventana.mensajeError(errorConex, false);
			}
		}else {
			ventana.mensajeError(error, false);
		}

	}
}
