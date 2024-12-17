package valueObjects;

import java.io.Serializable;

public class VOParticipanteBase  implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String nomArtistico;
	private int edad;
		
	public VOParticipanteBase() {
		
	}
	public VOParticipanteBase(String nomArtistico, int edad) {
		super();
		this.nomArtistico = nomArtistico;
		this.edad = edad;
	}
	
	public String getNomArtistico() {
		return nomArtistico;
	}
	
	public int getEdad() {
		return edad;
	}

}
