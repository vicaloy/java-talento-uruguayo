package logica;

import java.io.Serializable;


public class Nacional extends Participante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String departamento;
	String localidad;
	
	public Nacional(String nomArtistico, byte[] foto, String nomVerdadero, String apellidoVerdadero, int edad,
			String especialidadArtistica, int cantVotos, String departamento, String localidad) {
		super(nomArtistico, foto, nomVerdadero, apellidoVerdadero, edad, especialidadArtistica, cantVotos);
		this.departamento = departamento;
		this.localidad = localidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String tipoParticipante() {
		return this.getClass().getName();
	}
}
