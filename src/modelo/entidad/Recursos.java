package modelo.entidad;

public class Recursos {


    private String recurso;
    private int cantidad;

    public Recursos(String recurso, int cantidad) {
        this.recurso = recurso;
        this.cantidad = cantidad;
    }

    public Recursos() {
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Recursos{" +
                "recurso='" + recurso + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
