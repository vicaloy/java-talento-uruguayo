package GUI.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import GUI.Menu;
import GUI.ventanas.ListadoPerformancePart;
import logica.ICapaLogica;
import logica.excepciones.*;
import valueObjects.VOPerformance;

public class ControladorDefinirFinalistasRespaldar {
	private ICapaLogica fachada;
	private Menu ventana;

	private final String errorConex = "Se perdió la conexión con el servidor, intente nuevamente";
	private final String exitoFinalista = "Se definieron los finalistas exitosamente";
	private final String exitoRespaldo = "Se respaldaron los datos exitosamente";

	public ControladorDefinirFinalistasRespaldar(Menu ventana) {
		this.ventana = ventana;
		try {
			Properties p = new Properties();
			String nomArch = "config/archivo.properties";
			p.load(new FileInputStream(nomArch));
			String servidor = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + servidor + ":" + puerto + "/fachada";

			fachada = (ICapaLogica) Naming.lookup(ruta);

		} catch (IOException | NotBoundException e) {

			ventana.mensajeError(errorConex, false);
		}

	}

	public void definirFinalistas() {

		try {
			System.out.println("CONTROLADOR, antes de mandar a la fachada");
			fachada.definirFinalistas();
			System.out.println("CONTROLADOR, luego de mandar a la fachada");

			ventana.mensajeError(exitoFinalista, true);
		} catch (PartConMenosDe15PerfException e) {
			ventana.mensajeError(e.getMensaje(), false);
		} catch (DiccioMenosDeTresPartException e) {
			ventana.mensajeError(e.getMensaje(), false);
		} catch (RemoteException e) {
			ventana.mensajeError(errorConex, false);
		} catch (IOException e) {
			ventana.mensajeError(errorConex, false);
		}

	}

	public void respaldar() {
		try {
			System.out.println("CONTROLADOR, antes de mandar a la fachada");
			fachada.respaldarDatos();
			System.out.println("CONTROLADOR, luego de mandar a la fachada");

			ventana.mensajeError(exitoRespaldo, true);
		} catch (PersistenciaException e) {
			ventana.mensajeError(e.getMensaje(), false);
		} catch (RemoteException e) {
			ventana.mensajeError(errorConex, false);
		}

	}
	
	public void FinalizarConcurso() 
	{
		try {
			System.out.println("CONTROLADOR, antes de mandar a la fachada");
			fachada.finalizarVotacionDeterminarGanador();
			System.out.println("CONTROLADOR, luego de mandar a la fachada");

			ventana.mensajeError(exitoFinalista, true);
		} catch (RemoteException e) {
			ventana.mensajeError(errorConex, false);
		}
		catch (VotacionYaCerradaException e) {
			ventana.mensajeError(e.getMensaje(), false);
		}
		catch (PerformancesNoListasException e) {
			ventana.mensajeError(e.getMensaje(), false);
		}
		catch (FinalistasNoDefinidosException e) {
			ventana.mensajeError(e.getMensaje(), false);
		}
}
}
