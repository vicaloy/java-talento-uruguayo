package logica;

import java.awt.image.BufferedImage;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;

import logica.excepciones.*;
import persistencia.PersistenciaDatos;
import valueObjects.*;

public class CapaLogica extends UnicastRemoteObject implements ICapaLogica {

	private static final long serialVersionUID = 1L;
	private Participantes participantes;
	private Participante finalistaUno;
	private Participante finalistaDos;
	private Participante finalistaTres;
	private Participante ganador;
	private boolean performancesListas;
	private boolean votacionesCerradas;
	private Monitor monitor;

	public CapaLogica() throws RemoteException, IOException {
		PersistenciaDatos dx = new PersistenciaDatos();
		Properties p = new Properties();
		String nomArch;
		VOCapaLogica aux;

		try {
			p.load(new FileInputStream("config/archivo.properties"));
			nomArch = p.getProperty("nombreArchivo");

			aux = dx.recuperar(nomArch);

			if (aux != null) {
				participantes = aux.getParticipantes();
				finalistaUno = aux.getFinalistaUno();
				finalistaDos = aux.getFinalistaDos();
				finalistaTres = aux.getFinalistaTres();
				ganador = aux.getGanador();
				performancesListas = aux.getPerformancesListas();
				votacionesCerradas = aux.getVotacionesCerradas();
				monitor = new Monitor();
			} else {
				participantes = new Participantes();
				finalistaUno = null;
				finalistaDos = null;
				finalistaTres = null;
				ganador = null;
				performancesListas = false;
				votacionesCerradas = false;
				monitor = new Monitor();
			}
		} catch (FileNotFoundException ex) {
			participantes = new Participantes();
			finalistaUno = null;
			finalistaDos = null;
			finalistaTres = null;
			ganador = null;
			performancesListas = false;
			votacionesCerradas = false;
			monitor = new Monitor();
		}

	}


	public void registrarParticipante(VOParticipante voPart) throws RemoteException, ParticipanteYaExisteException {
		monitor.comienzoEscritura();
		System.out.println("FACHADA: antes de ingresar participante al diccionario");
		if (participantes.member(voPart.getNomArtistico())) {
			System.out.println("FACHADA: error, participante repetido");
			monitor.terminoEscritura();
			throw new ParticipanteYaExisteException("Error: El participante ya existe");
		} else {
			if (performancesListas == false) {
			System.out.println("FACHADA: efectivamente ingreso el participante");
			Participante part = null;
			if (voPart instanceof VOExtranjero) {
				part = new Extranjero(voPart.getNomArtistico(), voPart.getFoto(), voPart.getNomVerdadero(),
						voPart.getApellidoVerdadero(), voPart.getEdad(), voPart.getEspecialidadArtistica(), 0,
						((VOExtranjero) voPart).getPaisOrigen(), ((VOExtranjero) voPart).getAniosUru());
			} else {
				part = new Nacional(voPart.getNomArtistico(), voPart.getFoto(), voPart.getNomVerdadero(),
						voPart.getApellidoVerdadero(), voPart.getEdad(), voPart.getEspecialidadArtistica(), 0,
						((VONacional) voPart).getDepartamento(), ((VONacional) voPart).getLocalidad());
			}
			participantes.insert(part);
			}
			else
			{
				monitor.terminoEscritura();
				throw new ParticipanteYaExisteException("Error: Ya ha terminado el concurso");
			}
		}
		monitor.terminoEscritura();
	}

	public ArrayList<VOParticipanteListado> listarParticipantes() throws RemoteException, DiccioVacioException {
		monitor.comienzoLectura();
		if (participantes.empty()) {
			monitor.terminoLectura();
			throw new DiccioVacioException("Error: no hay participantes");
		} else {
			monitor.terminoLectura();
			return participantes.listarParticipantes();
		}
	}

	public VOParticipante buscarParticipante(String nom) throws RemoteException, ParticipanteNoExisteException {
		monitor.comienzoLectura();
		if (this.participantes.member(nom) == false) {
			monitor.terminoLectura();
			throw new ParticipanteNoExisteException("ERROR: no hay participante registrado con dicho nombre artistico");
		} else {
			Participante p = this.participantes.find(nom);
			VOParticipante vop;
			if (p instanceof Nacional) {
				vop = new VONacional(p.getNomArtistico(), p.getEdad(), p.getCantVotos(), p.getFoto(),
						p.getNomVerdadero(), p.getApellidoVerdadero(), p.getEspecialidadArtistica(),
						p.cantVotosJueces(), ((Nacional) p).getDepartamento(), ((Nacional) p).getLocalidad());
			} else {
				vop = new VOExtranjero(p.getNomArtistico(), p.getEdad(), p.getCantVotos(), p.getFoto(),
						p.getNomVerdadero(), p.getApellidoVerdadero(), p.getEspecialidadArtistica(),
						p.cantVotosJueces(), ((Extranjero) p).getPaisOrigen(), ((Extranjero) p).getAniosUruguay());
			}

			monitor.terminoLectura();
			return vop;
		}
	}

	public VOPerformance[] listarPerformancesParticipante(String nomArt)
			throws RemoteException, ParticipanteNoExisteException {
		monitor.comienzoLectura();
		if (participantes.member(nomArt)) {
			Participante p = participantes.find(nomArt);
			monitor.terminoLectura();
			return p.obtenerPerformances();
		} else {
			monitor.terminoLectura();
			throw new ParticipanteNoExisteException("Error: el participante con el nombre ingresado no existe"); // dar
																													// vuelta
		}
	}

	public void definirFinalistas()
			throws PartConMenosDe15PerfException, DiccioMenosDeTresPartException, IOException {
		monitor.comienzoEscritura();
		if(performancesListas == false) {
		if (!participantes.empty() && participantes.cantParticipantes() >= 3) {
			if (participantes.todosTienen15Performances()) {
				ArrayList<Participante> lista = participantes.tresMayorPuntaje();
				
				finalistaUno = lista.get(0);
				InputStream in = new ByteArrayInputStream(lista.get(0).getFoto());
				BufferedImage bufferImage;
				bufferImage = ImageIO.read(in);
				ImageIO.write(bufferImage, "jpg", new File("imagenes\\Fin1.jpg") );
				finalistaDos = lista.get(1);
				in = new ByteArrayInputStream(lista.get(1).getFoto());
				bufferImage = ImageIO.read(in);
				ImageIO.write(bufferImage, "jpg", new File("imagenes\\Fin2.jpg") );
				finalistaTres = lista.get(2);
				in = new ByteArrayInputStream(lista.get(2).getFoto());
				bufferImage = ImageIO.read(in);
				ImageIO.write(bufferImage, "jpg", new File("imagenes\\Fin3.jpg") );
				performancesListas = true;
			} else {
				monitor.terminoEscritura();
				throw new PartConMenosDe15PerfException("Error: hay participantes con menos de " + Performances.CANT_PERFORMANCES + "performances");
			}
		} else {
			monitor.terminoEscritura();
			throw new DiccioMenosDeTresPartException("Error: Hay menos de tres participantes");
		}
		}else {
			monitor.terminoEscritura();
			throw new DiccioMenosDeTresPartException("Error: Ya se han determinado los finalistas");
		}
		monitor.terminoEscritura();
	}

	public void ingresarPerformance(String nomArt, VOPerformance voPerf) throws RemoteException,
			SecuenciaCompletaException, ParticipanteNoExisteException, PerformancesCerradasException {
		monitor.comienzoEscritura();
		if (performancesListas == false) {
			if (participantes.member(nomArt)) {
				Participante p = participantes.find(nomArt);
				if (p.cantPerformances() < Performances.CANT_PERFORMANCES) {
					Performance perf = new Performance(voPerf.getDescripcion(), voPerf.getPuntajeUno(),
							voPerf.getPuntajeDos(), voPerf.getPuntajeTres());
					p.agregarPerformance(perf);
					monitor.terminoEscritura();
				} else {
					monitor.terminoEscritura();
					throw new SecuenciaCompletaException("Error: el participante ya tiene " + Performances.CANT_PERFORMANCES + " performances");
				}
			} else {
				monitor.terminoEscritura();
				throw new ParticipanteNoExisteException("Error: el participante con el nombre ingresado no existe");
			}
		} else {
			monitor.terminoEscritura();
			throw new PerformancesCerradasException("ERROR: Ya se cerraron las Performances");
		}
	}

	public void finalizarVotacionDeterminarGanador() throws RemoteException, VotacionYaCerradaException,
			PerformancesNoListasException, FinalistasNoDefinidosException {
		monitor.comienzoEscritura();
		if (votacionesCerradas == true) {
			monitor.terminoEscritura();
			throw new VotacionYaCerradaException("ERROR: La votacion ya esta cerrada");
		} else {
			if (performancesListas == false) {
				monitor.terminoEscritura();
				throw new PerformancesNoListasException(
						"ERROR: Al menos 1 participante no realizo sus " + Performances.CANT_PERFORMANCES + " performances");
			} else {
				if (finalistaUno == null | finalistaDos == null | finalistaTres == null) {
					monitor.terminoEscritura();
					throw new FinalistasNoDefinidosException("ERROR: Aun no se definieron los finalistas");
				} else {
					votacionesCerradas = true;

					int p1 = this.finalistaUno.getCantVotos();
					int p2 = this.finalistaDos.getCantVotos();
					int p3 = this.finalistaTres.getCantVotos();

					if (p1 > p2 && p1 > p3)
						ganador = finalistaUno;
					else {
						if (p2 > p1 && p2 > p3)
							ganador = finalistaDos;
						else {
							if (p3 > p1 && p3 > p2)
								ganador = finalistaTres;
							else {
								if (p1 == p3 && p1 > p2)
									ganador = finalistaUno;
								else if (p1 == p2 && p1 > p3)
									ganador = finalistaUno;
								else if (p2 == p3 && p2 > p1)
									ganador = finalistaDos;
								else
									ganador = finalistaUno;
							}

						}
					}

					monitor.terminoEscritura();

				}
			}
		}
	}

	public void respaldarDatos() throws RemoteException, PersistenciaException {
		monitor.comienzoLectura();
		VOCapaLogica voc = new VOCapaLogica(participantes, finalistaUno, finalistaDos, finalistaTres, ganador,
				performancesListas, votacionesCerradas);
		PersistenciaDatos dx = new PersistenciaDatos();
		Properties p = new Properties();
		String nomArch = "config/archivo.properties";
		try {
			p.load(new FileInputStream(nomArch));
			String archivo = p.getProperty("nombreArchivo");
			dx.respaldar(archivo, voc);
			monitor.terminoLectura();
		} catch (IOException e) {
			monitor.terminoLectura();
			throw new PersistenciaException("Error al respaldar");
		}
	}

	public ArrayList<VOParticipanteFinalista> listarFinalistasYGanador() throws RemoteException, FinalistasNoDefinidosException 
	{
		monitor.comienzoLectura();
		
		if (finalistaUno == null | finalistaDos == null | finalistaTres == null) 
		{
			monitor.terminoLectura();
			throw new FinalistasNoDefinidosException("ERROR: Aun no se definieron los finalistas");
		} 
		else 
		{
			ArrayList<VOParticipanteFinalista> resu = new ArrayList<VOParticipanteFinalista>();

			VOParticipanteFinalista uno = new VOParticipanteFinalista(finalistaUno.getNomArtistico(),
					finalistaUno.getEdad(), finalistaUno.getCantVotos(), finalistaUno.getFoto());
			resu.add(uno);
			VOParticipanteFinalista dos = new VOParticipanteFinalista(finalistaDos.getNomArtistico(),
					finalistaDos.getEdad(), finalistaDos.getCantVotos(), finalistaDos.getFoto());
			resu.add(dos);
			VOParticipanteFinalista tres = new VOParticipanteFinalista(finalistaTres.getNomArtistico(),
					finalistaTres.getEdad(), finalistaTres.getCantVotos(), finalistaTres.getFoto());
			resu.add(tres);

			if (ganador != null) 
			{
				VOParticipanteFinalista gan = new VOParticipanteFinalista(ganador.getNomArtistico(), ganador.getEdad(),
						ganador.getCantVotos(), ganador.getFoto());
				resu.add(gan);
			}
			else
			{
				VOParticipanteFinalista gan = null;
				resu.add(gan);
			}
			monitor.terminoLectura();
			return resu;
		}
	}

	public void votarFinalista(String nom) throws RemoteException, VotacionYaCerradaException,
			FinalistasNoDefinidosException, ParticipanteNoExisteException {
		monitor.comienzoEscritura();
		if (votacionesCerradas == true) {
			monitor.terminoEscritura();
			throw new VotacionYaCerradaException("ERROR: La votacion ya finalizo");
		} else {
			if (finalistaUno == null | finalistaDos == null | finalistaTres == null) {
				monitor.terminoEscritura();
				throw new FinalistasNoDefinidosException("ERROR: Aun no se definieron los finalistas");
			} else {
				if (!participantes.member(nom)) {
					monitor.terminoEscritura();
					throw new ParticipanteNoExisteException("Error: El participante no existe");
				} else {
					participantes.find(nom).agregarVotoPublico();
					monitor.terminoEscritura();
				}
			}
		}
	}
}
