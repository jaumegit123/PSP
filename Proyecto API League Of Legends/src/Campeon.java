
public class Campeon {

	private int id;
	private String nombre;
	private String png;

	public Campeon(int id, String nombre, String png) {
		this.id = id;
		this.nombre = nombre;
		this.png = png;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPng() {
		return png;
	}

	public void setPng(String png) {
		this.png = png;
	}

}
