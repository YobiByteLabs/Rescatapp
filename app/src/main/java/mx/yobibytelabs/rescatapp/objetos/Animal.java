package mx.yobibytelabs.rescatapp.objetos;

public class Animal {
	private String nombre;
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private int drawableImageID;

	public Animal(String titulo,String nombre, int drawableImageID) {
		this.nombre = nombre;
		this.drawableImageID = drawableImageID;
        this.titulo = titulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDrawableImageID() {
		return drawableImageID;
	}

	public void setDrawableImageID(int drawableImageID) {
		this.drawableImageID = drawableImageID;
	}

}
