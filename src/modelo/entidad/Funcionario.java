package modelo.entidad;

public class Funcionario {

    private Long identificacion;
    private String nombre;
    private String cargo;
    private int edad;

    public Funcionario(Long identificacion, String nombre, String cargo, int edad) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.cargo = cargo;
        this.edad = edad;
    }

    public Funcionario() {

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Funcionarios{" +
                "identificacion=" + identificacion +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", edad=" + edad +
                '}';
    }
}
