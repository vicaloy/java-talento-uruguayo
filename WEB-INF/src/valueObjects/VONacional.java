package valueObjects;

import java.io.Serializable;

public class VONacional extends VOParticipante  implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String departamento;
	private String localidad;
	
	public VONacional(String nomArtistico, int edad, int cantVotos, byte[] foto, String nombreVerdadero,
			String apellidoVerdadero, String espArtista, int votosJueces, String departamento, String localidad) {
		super(nomArtistico, edad, cantVotos, foto, nombreVerdadero, apellidoVerdadero, espArtista, votosJueces);
		this.departamento = departamento;
		this.localidad = localidad;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public String getLocalidad() {
		return localidad;
	}

}
