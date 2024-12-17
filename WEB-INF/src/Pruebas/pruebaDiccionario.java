package Pruebas;
import java.io.IOException;
import java.util.ArrayList;

import logica.Extranjero;
import logica.Nacional;
import logica.Participante;
import logica.Participantes;
import logica.Performance;
import logica.Performances;
import valueObjects.VOExtranjero;
import valueObjects.VONacional;
import valueObjects.VOParticipante;
import valueObjects.VOParticipanteBase;
import valueObjects.VOPerformance;
import persistencia.*;

public class pruebaDiccionario 
{

	public static void main(String[] args) throws IOException 
	{
		
		Participantes p=new Participantes();
		
		Participante a = new Extranjero("Chucky", null, "Juan", "Perez", 21, "Salto", 10, "Argentina", 2);
		Participante b = new Extranjero("Harry Potter", null, "Diego", "Gonzalez", 33, "Magia", 13, "Brasil", 4);
		Participante c = new Nacional("Gretel", null, "Monica", "Diosa", 41, "Tiro al blanco", 19, "Montevideo", "Montievideo");
		Participante d = new Nacional("Handsel", null, "Dario", "Muñoz", 34, "Arco y flecha", 17, "Montevideo", "Pajas blancas");
		Participante e = new Extranjero("aaaa", null, "aaa", "aaa", 123, "aaa", 123, "aaaa", 123);
		Participante f = new Nacional("fff", null, "fff", "fff", 123, "fff", 123, "fff", "fff");
		Participante g = new Nacional("ggg", null, "ggg", "ggg", 333, "ggg", 222, "ggg", "ggg");
		
		
		if(p.empty())
			System.out.println("Diccionario vacio");
		else
			System.out.println("Diccionario no vacio");
		
		
		Performance aa = new Performance("aaa", 1, 14, 153);
		Performance bb = new Performance("bbb", 224, 322, 5342);
		Performance cc = new Performance("ccc", 3321, 263, 334);
		Performance dd = new Performance("ddd", 3423, 343, 553);
		Performance ee = new Performance("eee", 233, 364, 3342);
		Performance ff = new Performance("fff", 5343, 573, 973);
		Performance gg = new Performance("ggg", 463, 354, 367);
		
		
		a.agregarPerformance(aa);
		b.agregarPerformance(bb);
		c.agregarPerformance(cc);
		d.agregarPerformance(dd);
		e.agregarPerformance(ee);
		f.agregarPerformance(ff);
		g.agregarPerformance(gg);
		
		p.insert(a);
		p.insert(b);
		p.insert(c);
		p.insert(d);
		p.insert(e);
		p.insert(f);
		p.insert(g);
		
		if(p.empty())
			System.out.println("Diccionario vacio");
		else
			System.out.println("Diccionario no vacio");
		
		
		System.out.print("\nParticipantes:\n\n");
		
		for(VOParticipanteBase v: p.listarParticipantes()) 
		{
			if(v instanceof VOExtranjero) 
			{
				System.out.println(v.getNomArtistico()); // listar todos los datos
			}else 
			{
				System.out.println(v.getNomArtistico()); // listar todos los datos
			}
			
		}

		// cargarle mas de 4 participantes al diccionario con puntajes bien distintos
		// y volver a probar en detalle este codigo para quedarnos tranquilos de que
		// siempre calcula bien los 3 finalistas
		
		System.out.print("\nFinalistas:\n\n");
		
		ArrayList<Participante> aux = p.tresMayorPuntaje();
		for(Participante v: aux) 
		{
			if(v instanceof Extranjero)
			{
				System.out.println(v.getNomArtistico() + "-" + v.getNomVerdadero() + "-" + 
				v.getApellidoVerdadero() + "-" + v.getCantVotos() + "-" + v.getEdad() + "-" + 
				v.getEspecialidadArtistica() + "-" + ((Extranjero) v).getAniosUruguay() +  "-" + ((Extranjero) v).getPaisOrigen());
			}
			else
			{
				System.out.println(v.getNomArtistico() + "-" + v.getNomVerdadero() + "-" + 
				v.getApellidoVerdadero() + "-" + v.getCantVotos() + "-" + v.getEdad() + "-" + 
				v.getEspecialidadArtistica() + "-" + ((Nacional)v).getDepartamento() + "-" + ((Nacional)v).getLocalidad());
			}
		}
		
		
		//PersistenciaDatos dx = new PersistenciaDatos();
		//dx.respaldar("Datos.txt", p);
		
		
		
		//dx.respaldar(nomArch, p);
	}
}
