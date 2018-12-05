package activitat4;

public class Aeropuerto {

	private String id, nomAeroport, nomCiutat, nomPais, sigles, coordX, coordY;

	public Aeropuerto(String id, String nomAeroport, String nomCiutat, String nomPais, String sigles, String coordX, String coordY) {
		this.id = id;
		this.nomAeroport = nomAeroport;
		this.nomCiutat = nomCiutat;
		this.nomPais = nomPais;
		this.sigles = sigles;
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public Aeropuerto(Aeropuerto a) {
		this.id = a.id;
		this.nomAeroport = a.nomAeroport;
		this.nomCiutat = a.nomCiutat;
		this.nomPais = a.nomPais;
		this.sigles = a.sigles;
		this.coordX = a.coordX;
		this.coordY = a.coordY;
	}

	public String getId() {
		return id;
	}

	public String getNomAeroport() {
		return nomAeroport;
	}

	public String getNomCiutat() {
		return nomCiutat;
	}

	public String getNomPais() {
		return nomPais;
	}

	public String getSigles() {
		return sigles;
	}

	public String getCoordX() {
		return coordX;
	}

	public String getCoordY() {
		return coordY;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNomAeroport(String nomAeroport) {
		this.nomAeroport = nomAeroport;
	}

	public void setNomCiutat(String nomCiutat) {
		this.nomCiutat = nomCiutat;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	public void setSigles(String sigles) {
		this.sigles = sigles;
	}

	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}

	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}

	@Override
	public String toString() {
		return "ID: " + id + " - " + nomAeroport + " - Ciutat: " + nomCiutat + " - País: " + nomPais + " - Sigles: "
				+ sigles;
	}

}
