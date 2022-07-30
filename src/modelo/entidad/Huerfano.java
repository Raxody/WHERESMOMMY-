package modelo.entidad;

public class Huerfano {
    private Long identificacion;
    private String nombre;
    private int edad;

    public Huerfano(Long identificacion, String nombre, int edad) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Huerfano() {

    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Huerfano{" +
                "identificacion=" + identificacion +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
