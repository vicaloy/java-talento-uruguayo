package mainServidorCliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import logica.CapaLogica;

public class Servidor
{
	public static void main(String[] args)
	{
		try
		{
			Properties p = new Properties();
			String nomArch = "config/archivo.properties";
			p.load(new FileInputStream(nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			int port = Integer.parseInt(puerto);
			
			LocateRegistry.createRegistry(port);
			
			CapaLogica fachada = new CapaLogica();
			String ruta = "//" + ip + ":" + port + "/fachada";
			System.out.println("Antes de publicar fachada");
			Naming.rebind(ruta, fachada);
			System.out.println("Despues de publicar fachada");
		}
		catch(MalformedURLException ex)	// URL mal (ruta)
		{
			System.out.println(ex.getMessage());
		}
		catch(RemoteException ex)	// no conecta
		{
			System.out.println(ex.getMessage());
		}
		catch (FileNotFoundException ex) // si no encuentra el archivo de configuracion
		{
			System.out.println(ex.getMessage());
		}
		catch (IOException ex) // si ocurre cualquier otro error de E/S
		{
			System.out.println(ex.getMessage());
		}
	}
}
