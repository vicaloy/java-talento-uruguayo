package logica;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.io.Serializable;



import valueObjects.*;


public class Participantes implements Serializable 
{
		
	private static final long serialVersionUID = 1L;
	TreeMap<String, Participante> diccio;
	
	public Participantes() 
	{
		this.diccio=new TreeMap<String, Participante>();
	}
	
	public boolean empty() 
	{
		return this.diccio.isEmpty();
	}
	
	public void insert(Participante participante) 
	{
		this.diccio.put(participante.getNomArtistico(), participante);
	}
	
	public boolean member(String nomArt) 
	{
		return this.diccio.containsKey(nomArt);
	}
	
	public Participante find(String nomArt) 
	{
		return this.diccio.get(nomArt);
	}
	
	public int cantParticipantes() 
	{
		return this.diccio.size();
	}
	
	public boolean todosTienen15Performances() 
	{
		Iterator<Participante> iter = diccio.values().iterator();
		boolean todosTienen15Perf=true;
		while (iter.hasNext() && todosTienen15Perf)
		{
			Participante p = iter.next();
			if(p.cantPerformances() < Performances.CANT_PERFORMANCES) { 
				todosTienen15Perf = false;
			}
		}
		return todosTienen15Perf;
	}
	
	public ArrayList<Participante> tresMayorPuntaje()		//PREC: cantParticipantes >= 3 && todosTienen15Performances == true
	{
		ArrayList<Participante> resu=null;
		

		Participante partUno, partDos, partTres;
		int ptosUno, ptosDos, ptosTres;
		partUno=(Participante) diccio.values().toArray()[0];
		partDos=(Participante) diccio.values().toArray()[1];
		partTres=(Participante) diccio.values().toArray()[2];
				
		ptosUno=partUno.cantVotosJueces();
		ptosDos=partDos.cantVotosJueces();
		ptosTres=partTres.cantVotosJueces();
				
		for(int i=3; i<cantParticipantes(); i++) 
		{
			Participante partAux = (Participante) diccio.values().toArray()[i];
			if(ptosUno < ptosDos && ptosUno < ptosTres)
			{
				if(partAux.cantVotosJueces() > ptosUno) 
				{
					ptosUno = partAux.cantVotosJueces();
					partUno = partAux;
				}
		        //menor 1;
			}
			else
			{
				if(ptosDos < ptosUno && ptosDos < ptosTres)
				{
					if(partAux.cantVotosJueces() > ptosDos) 
					{
						ptosDos = partAux.cantVotosJueces();
						partDos = partAux;
					}
		            //menor 2;
				}
				else
				{
					if(partAux.cantVotosJueces()> ptosTres) 
					{
						ptosTres=partAux.cantVotosJueces();
						partTres=partAux;
					}
		            //menor 3;
				}
			}
		}
			
		resu=new ArrayList<Participante>();
		resu.add(partUno);
		resu.add(partDos);
		resu.add(partTres);
			
		return resu;
	}
	
	public ArrayList<VOParticipanteListado> listarParticipantes()
	{
		
		ArrayList<VOParticipanteListado> resu=new ArrayList<VOParticipanteListado>();
		Iterator<Participante> iter = this.diccio.values().iterator();

		while (iter.hasNext())
		{
			Participante p = iter.next();
			
			VOParticipanteListado vop=null;
			vop= new VOParticipanteListado(p.getNomArtistico(), p.getEdad(), p.getEspecialidadArtistica());
			
			resu.add(vop);
		}
		
		return resu;
	}
}
	
	

