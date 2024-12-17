package logica;

import valueObjects.VOPerformance;
import java.io.Serializable;


public abstract class Participante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomArtistico;
	private byte[] foto;
	private String nomVerdadero;
	private String apellidoVerdadero;
	private int edad;
	private String especialidadArtistica;
	private int cantVotos;
	private Performances susPerformances;
	
	
	
	public Participante(String nomArtistico, byte[] foto, String nomVerdadero, String apellidoVerdadero, int edad, String especialidadArtistica, int cantVotos) 
	{
		super();
		this.nomArtistico = nomArtistico;
		this.foto = foto;
		this.nomVerdadero = nomVerdadero;
		this.apellidoVerdadero = apellidoVerdadero;
		this.edad = edad;
		this.especialidadArtistica = especialidadArtistica;
		this.cantVotos = cantVotos;
		this.susPerformances=new Performances();
	}
	
	public String getNomArtistico() {
		return nomArtistico;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public String getNomVerdadero() {
		return nomVerdadero;
	}
	
	public String getApellidoVerdadero() {
		return apellidoVerdadero;
	}
	
	public int getEdad() {
		return edad;
	}

	public String getEspecialidadArtistica() {
		return especialidadArtistica;
	}
	
	public int getCantVotos() {
		return cantVotos;
	}
	
	
	public VOPerformance[] obtenerPerformances() 
	{	
		return this.susPerformances.obtenerPerformances();	
	}
	
	public int cantPerformances() {
		return this.susPerformances.largo();
	}
	
	
	public int cantVotosJueces() {
		return this.susPerformances.sumarPuntos();
	}
	
	
	public void agregarPerformance(Performance p) {
		this.susPerformances.insertar(p);
	}
	
	public void agregarVotoPublico() {
		this.cantVotos++;
	}
	
	public abstract String tipoParticipante();
	
}
	
