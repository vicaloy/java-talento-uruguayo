package logica;

import java.io.Serializable;


public class Performance implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int numeroPerformance;
	private String descripcion;
	private int puntajeUno;
	private int puntajeDos;
	private int puntajeTres;

	public Performance() {
		
	}
	
	public Performance(String descripcion, int puntajeUno, int puntajeDos, int puntajeTres) {
		super();
		this.numeroPerformance = 0;
		this.descripcion = descripcion;
		this.puntajeUno = puntajeUno;
		this.puntajeDos = puntajeDos;
		this.puntajeTres = puntajeTres;
	}

	public int getNumeroPerformance() {
		return numeroPerformance;
	}

	public void setNumeroPerformance(int numeroPerformance) {
		this.numeroPerformance = numeroPerformance;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getPuntajeUno() {
		return puntajeUno;
	}

	public int getPuntajeDos()
	{
		return puntajeDos;
	}
	
	public int getPuntajeTres()
	{
		return puntajeTres;
	}
}
