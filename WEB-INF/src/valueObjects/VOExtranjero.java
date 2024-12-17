package valueObjects;

import java.io.Serializable;

public class VOExtranjero extends VOParticipante  implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String paisOrigen;
	private int aniosUru;
	
	public VOExtranjero(String nomArtistico, int edad, int cantVotos, byte[] foto, String nombreVerdadero,
			String apellidoVerdadero, String espArtista, int votosJueces, String paisOrigen, int aniosUru) {
		super(nomArtistico, edad, cantVotos, foto, nombreVerdadero, apellidoVerdadero, espArtista, votosJueces);
		this.paisOrigen = paisOrigen;
		this.aniosUru = aniosUru;
	}
	
	public String getPaisOrigen() {
		return paisOrigen;
	}
	
	public int getAniosUru() {
		return aniosUru;
	}

}
