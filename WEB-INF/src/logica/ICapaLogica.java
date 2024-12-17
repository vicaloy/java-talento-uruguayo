package logica;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import logica.excepciones.*;
import valueObjects.*;

public interface ICapaLogica extends Remote
{
	public void registrarParticipante(VOParticipante voPart) throws RemoteException, ParticipanteYaExisteException;
	public ArrayList<VOParticipanteListado> listarParticipantes() throws RemoteException, DiccioVacioException;
	public VOParticipante buscarParticipante(String nom) throws RemoteException, ParticipanteNoExisteException;
	public VOPerformance[] listarPerformancesParticipante(String nomArt) throws RemoteException, ParticipanteNoExisteException;
	public void definirFinalistas() throws RemoteException, PartConMenosDe15PerfException,  DiccioMenosDeTresPartException, IOException;
	public void ingresarPerformance(String nomArt, VOPerformance voPerf) throws RemoteException, SecuenciaCompletaException, ParticipanteNoExisteException,  PerformancesCerradasException;
	public void finalizarVotacionDeterminarGanador() throws RemoteException, VotacionYaCerradaException, PerformancesNoListasException, FinalistasNoDefinidosException;
	public void respaldarDatos() throws RemoteException, PersistenciaException;
	public ArrayList<VOParticipanteFinalista> listarFinalistasYGanador() throws RemoteException, FinalistasNoDefinidosException;
	public void votarFinalista(String nom) throws RemoteException, VotacionYaCerradaException, FinalistasNoDefinidosException, ParticipanteNoExisteException;
}