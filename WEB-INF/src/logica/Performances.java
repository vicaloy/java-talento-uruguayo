package logica;

import valueObjects.VOPerformance;
import java.io.Serializable;


public class Performances implements Serializable
{

	private static final long serialVersionUID = 1L;
	public static final int CANT_PERFORMANCES = 15;
	
	private Performance [] arre;
	private int tope;
	
	
	public Performances() 
	{
		super();
		this.arre = new Performance[Performances.CANT_PERFORMANCES];
		this.tope = 0;
	}
	
	public boolean esVacia()
	{
		return tope==0;
	}
	
	public int largo()
	{
		return tope;
	}
	
	public void insertar(Performance p)
	{
		p.setNumeroPerformance(tope + 1);
		arre[tope]=p;
		tope++;
	}
	
	public Performance kEsimo(int i)
	{
		return arre[i];
	}
	
	public int sumarPuntos()
	{
		int total=0;
		for(int i=0; i<tope; i++)
		{
			total=total + arre[i].getPuntajeUno() + arre[i].getPuntajeDos() + arre[i].getPuntajeTres();
		}
		return total;
	}
	
	public VOPerformance[] obtenerPerformances()
	{
		VOPerformance aux []=new VOPerformance[tope];
		
		for(int i=0; i<tope; i++)
		{
			aux[i]= new VOPerformance(arre[i].getNumeroPerformance(), arre[i].getDescripcion(), arre[i].getPuntajeUno(), arre[i].getPuntajeDos(), arre[i].getPuntajeTres());
		}
		return aux;
	}
	
}
