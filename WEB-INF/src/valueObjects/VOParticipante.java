package valueObjects;

import java.io.Serializable;

public class VOParticipante extends VOParticipanteFinalista  implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String nomVerdadero;
	private String apellidoVerdadero;
	private String especialidadArtistica;
	private int votosJueces;
	
	public VOParticipante(String nomArtistico, int edad, int cantVotos, byte[] foto, String  nombreVerdadero,
			String apellidoVerdadero, String espArtista, int votosJueces) {
		super(nomArtistico, edad, cantVotos, foto);
		this.nomVerdadero = nombreVerdadero;
		this.apellidoVerdadero = apellidoVerdadero;
		this.especialidadArtistica = espArtista;
		this.votosJueces = votosJueces;
	}
	
	public String getNomVerdadero() {
		return nomVerdadero;
	}
	
	public String getApellidoVerdadero() {
		return apellidoVerdadero;
	}
	
	public String getEspecialidadArtistica() {
		return especialidadArtistica;
	}
	
	public int getVotosJueces() {
		return votosJueces;
	}

}
