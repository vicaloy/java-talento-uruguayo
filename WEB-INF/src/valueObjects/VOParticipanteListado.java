package valueObjects;

public class VOParticipanteListado extends VOParticipanteBase 
{
	private static final long serialVersionUID = 1L;
	
	private String especialidadArtistica;
	
	public VOParticipanteListado(String nomArtistico, int edad, String especialidadArtistica) {
		super(nomArtistico,edad);
		this.especialidadArtistica = especialidadArtistica;
	}
	
	public String getEspecialidadArtistica() {
		return especialidadArtistica;
	}
}
