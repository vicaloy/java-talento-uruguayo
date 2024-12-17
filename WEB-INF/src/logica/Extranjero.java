package logica;

import java.io.Serializable;


public class Extranjero extends Participante implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String paisOrigen;
	int aniosUruguay;
	
	public Extranjero(String nomArtistico, byte[] foto, String nomVerdadero, String apellidoVerdadero, int edad,
			String especialidadArtistica, int cantVotos, String paisOrigen, int aniosUruguay) {
		super(nomArtistico, foto, nomVerdadero, apellidoVerdadero, edad, especialidadArtistica, cantVotos);
		this.paisOrigen = paisOrigen;
		this.aniosUruguay = aniosUruguay;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public int getAniosUruguay() {
		return aniosUruguay;
	}
	
	public String tipoParticipante() {
		return this.getClass().getName();
	}

}
