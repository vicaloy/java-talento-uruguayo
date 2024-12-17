package valueObjects;

import java.io.Serializable;

public class VOParticipanteFinalista extends VOParticipanteBase implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private int cantVotos;
	private byte[] foto;
	
	public VOParticipanteFinalista() {
		
	}
	
	public VOParticipanteFinalista(String nomArtistico, int edad, int cantVotos, byte[] foto) {
		super(nomArtistico, edad);
		this.cantVotos = cantVotos;
		this.foto = foto;
	}
	
	public int getCantVotos() 
	{
		return cantVotos;
	}
	
	public byte[] getFoto() {
		return foto;
	}
}
