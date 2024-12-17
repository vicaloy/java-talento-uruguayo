package valueObjects;

import java.io.Serializable;

public class VOPerformance  implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private int numeroPerformance;
	private String descripcion;
	private int puntajeUno;
	private int puntajeDos;
	private int puntajeTres;
	
	public VOPerformance(int numeroPerformance, String descripcion, int puntajeUno, int puntajeDos, int puntajeTres) 
	{
		super();
		this.numeroPerformance = numeroPerformance;
		this.descripcion = descripcion;
		this.puntajeUno = puntajeUno;
		this.puntajeDos = puntajeDos;
		this.puntajeTres = puntajeTres;
	}

	public int getNumeroPerformance() {
		return numeroPerformance;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getPuntajeUno() {
		return puntajeUno;
	}

	public int getPuntajeDos() {
		return puntajeDos;
	}

	public int getPuntajeTres() {
		return puntajeTres;
	}

	
	
	
	
}
