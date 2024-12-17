package GUI.controladores;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Properties;

import GUI.ventanas.RegistrarParticipante;
import logica.ICapaLogica;
import logica.excepciones.ParticipanteNoExisteException;
import logica.excepciones.ParticipanteYaExisteException;
import valueObjects.*;

public class ControladorRegistrarParticipante {
	private ICapaLogica fachada;
	private RegistrarParticipante ventana;

	private final String errorNomVerdadero = "Ingrese nombre verdadero por favor. \n";
	private final String errorNomArtistico = "Ingrese nombre artístico por favor. \n";
	private final String errorApellido = "Ingrese nombre artístico por favor. \n";
	private final String errorEdad = "Ingrese edad por favor. \n";
	private final String errorEspecialidad = "Ingrese especialidad por favor. \n";
	private final String errorPaisOrigen = "Ingrese pais de origen por favor. \n";
	private final String errorAnosUru = "Ingrese años en Uruguay por favor. \n";
	private final String errorDepto = "Ingrese departamento por favor. \n";
	private final String errorLocalidad = "Ingrese localidad por favor. \n";
	private final String exito = "Se registró el participante exitosamente";
	private final String errorConex = "Se perdió la conexión con el servidor, intente nuevamente";

	public ControladorRegistrarParticipante(RegistrarParticipante ventana) {
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
			/*
			 * Si salta alguna de las excepciones, mandarle a la ventana un mensaje propio
			 * en español
			 */
			ventana.mensajeError(errorConex, false);
		}

	}

	public void registrarParticipanteNacional(String nomArt, String nom, String ape, int edad, String esp, String dep,
			String loc, byte[] foto) {

		String error = "";
		if (nomArt == null || nomArt.isEmpty()) {
			error = errorNomArtistico;
		}

		if (nom == null || nom.isEmpty()) {
			error += errorNomVerdadero;
		}

		if (ape == null || ape.isEmpty()) {
			error += errorApellido;
		}

		if (edad == 0) {
			error += errorEdad;
		}

		if (esp == null || esp.isEmpty()) {
			error += errorEspecialidad;
		}

		if (dep == null || dep.isEmpty()) {
			error += errorDepto;
		}

		if (loc == null || loc.isEmpty()) {
			error += errorLocalidad;
		}

		if (error.isEmpty()) {

			VOParticipante part = new VONacional(nomArt.toUpperCase(), edad, 0, foto, nom.toUpperCase(), ape.toUpperCase(), esp.toUpperCase(), 0, dep.toUpperCase(), loc.toUpperCase());

			try {
				fachada.registrarParticipante(part);
				ventana.mensajeError(exito, true);
				/*
				 * si registro correctamente al participante, mandarle a la ventana un mensaje
				 * de exito y luego cerrarla
				 */
			} catch (ParticipanteYaExisteException e) {
				/*
				 * separar este catch en dos, para mandarle a la ventana mensajes de error
				 * diferentes. Si salta la RemoteException, mandarle a la ventana un mensaje
				 * propio en español
				 */

				ventana.mensajeError(e.getMensaje(), false);
			} catch (RemoteException e) {
				ventana.mensajeError(errorConex, false);
			}

		} else {

			ventana.mensajeError(error, false);
		}
	}

	public void registrarParticipanteExtranjero(String nomArt, String nom, String ape, int edad, String esp,
			String pais, int anios, byte[] foto) {

		String error = "";
		if (nomArt == null || nomArt.isEmpty()) {
			error = errorNomArtistico;
		}

		if (nom == null || nom.isEmpty()) {
			error += errorNomVerdadero;
		}

		if (ape == null || ape.isEmpty()) {
			error += errorApellido;
		}

		if (edad == 0) {
			error += errorEdad;
		}

		if (esp == null || esp.isEmpty()) {
			error += errorEspecialidad;
		}

		if (pais == null || pais.isEmpty()) {
			error += errorPaisOrigen;
		}

		if (anios == 0) {
			error += errorAnosUru;
		}

		if (error.isEmpty()) {

			VOParticipante part = new VOExtranjero(nomArt.toUpperCase(), edad, 0, foto, nom.toUpperCase(), ape.toUpperCase(), esp.toUpperCase(), 0, pais.toUpperCase(), anios);

			try {
				System.out.println("CONTROLADOR, antes de mandar a la fachada");
				fachada.registrarParticipante(part);
				System.out.println("CONTROLADOR, luego de mandar a la fachada");

				ventana.mensajeError(exito, true);
			} catch (ParticipanteYaExisteException e) {
				/*
				 * separar este catch en dos, para mandarle a la ventana mensajes de error
				 * diferentes. Si salta la RemoteException, mandarle a la ventana un mensaje
				 * propio en español
				 */

				ventana.mensajeError(e.getMensaje(), false);
			} catch (RemoteException e) {
				ventana.mensajeError(errorConex, false);
			}
		} else {
			ventana.mensajeError(error, false);
		}
	}
}
