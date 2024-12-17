package pruebaRMI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;
import logica.ICapaLogica;
import logica.excepciones.DiccioVacioException;
import logica.excepciones.ParticipanteNoExisteException;
import logica.excepciones.ParticipanteYaExisteException;
import logica.excepciones.PerformancesCerradasException;
import logica.excepciones.SecuenciaCompletaException;
import valueObjects.*;


public class Cliente
{
	public static void main(String[] args)
	{
		try
		{
			Properties p = new Properties();
			String nomArch = "config/archivo.properties";
			p.load(new FileInputStream(nomArch));
			String servidor = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + servidor + ":" + puerto + "/fachada";
			
			ICapaLogica fachada = (ICapaLogica) Naming.lookup(ruta);
			
			VOParticipante a = new VONacional("aaa", 12, 0, null, "aaa", "aaa", "aaa", 0, "aaa", "aaa");
			VOPerformance p1 = new VOPerformance(0, "111", 32, 424, 52);
			
			try
			{
				fachada.registrarParticipante(a);
				fachada.ingresarPerformance("aaa", p1);
				
				ArrayList<VOParticipanteListado> part= new ArrayList<VOParticipanteListado>();
				part = fachada.listarParticipantes();
				
				for(int i=0; i<part.size(); i++)
				{
					System.out.println("Nom. Art: " + part.get(i).getNomArtistico() + ", Edad: " + part.get(i).getEdad() + ", Esp: " + part.get(i).getEspecialidadArtistica());
				}
				
				//fachada.respaldarDatos();
			}
			catch(ParticipanteYaExisteException e)
			{
				System.out.println(e.getMensaje());
			} 
			catch (SecuenciaCompletaException e)
			{
				System.out.println(e.getMensaje());
			} 
			catch (ParticipanteNoExisteException e)
			{
				System.out.println(e.getMensaje());
			} 
			catch (PerformancesCerradasException e)
			{
				System.out.println(e.getMensaje());
			}
			catch (DiccioVacioException e)
			{
				System.out.println(e.getMensaje());
			}
		}
		catch(RemoteException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		} 
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		} 
		catch (NotBoundException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
