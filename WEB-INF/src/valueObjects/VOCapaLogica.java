package valueObjects;

import logica.Participante;
import logica.Participantes;
import java.io.Serializable;

public class VOCapaLogica implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Participantes participantes;
	private Participante finalistaUno;
	private Participante finalistaDos;
	private Participante finalistaTres;
	private Participante ganador;
	private boolean performancesListas;
	private boolean votacionesCerradas;
	
	public VOCapaLogica (Participantes p, Participante uno, Participante dos, Participante tres, Participante ganador, boolean performances, boolean votacion) 
	{		
		this.participantes = p;
		this.finalistaUno = uno;
		this.finalistaDos = dos;
		this.finalistaTres = tres;
		this.ganador = ganador;
		this.performancesListas = performances;
		this.votacionesCerradas = votacion;		
	}
	
	public Participantes getParticipantes() {
		return participantes;
	}

	public Participante getFinalistaDos() {
		return finalistaDos;
	}

	public Participante getFinalistaUno() {
		return finalistaUno;
	}

	public Participante getFinalistaTres() {
		return finalistaTres;
	}


	public Participante getGanador() {
		return ganador;
	}

	public boolean getPerformancesListas() {
		return performancesListas;
	}

	public boolean getVotacionesCerradas() {
		return votacionesCerradas;
	}
	
	
}
