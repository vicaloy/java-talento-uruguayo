package Pruebas;
import logica.Performance;
import logica.Performances;
import valueObjects.VOPerformance;

public class pruebaSecuencia 
{

	public static void main(String[] args) 
	{
		Performances p = new Performances();
		
		Performance a = new Performance("aaa", 1, 1, 1);
		Performance b = new Performance("bbb", 2, 2, 2);
		Performance c = new Performance("ccc", 3, 3, 3);
		
		if(p.esVacia())
			System.out.println("Secuencia vacia");
		else
			System.out.println("Secuencia no vacia");
		
		p.insertar(a);
		p.insertar(b);
		p.insertar(c);
		
		if(p.esVacia())
			System.out.println("Secuencia vacia");
		else
			System.out.println("Secuencia no vacia");

		for(int i=0; i<p.largo(); i++)
		{
			System.out.println("Nro.: " + p.kEsimo(i).getNumeroPerformance() + ", Desc: " + p.kEsimo(i).getDescripcion() + ", Punt. Uno: " + p.kEsimo(i).getPuntajeUno() + ", Punt. Dos: " + p.kEsimo(i).getPuntajeDos() + ", Punt. Tres: " + p.kEsimo(i).getPuntajeTres());
		}
		
		System.out.println("Puntos totales: " + p.sumarPuntos());
		
		VOPerformance vo[] = p.obtenerPerformances();
		
		for(int i=0; i<p.largo(); i++)
		{
			System.out.println("Nro.: " + vo[i].getNumeroPerformance() + ", Desc: " + vo[i].getDescripcion() + ", P1: " + vo[i].getPuntajeUno() + ", P2: " + vo[i].getPuntajeDos() + ", P3: " + vo[i].getPuntajeTres());
		}
		
		
	}
}
