package modelo.entidad;

public class Adopcion {
    private Long identificacionHuerfano;
    private Long telefonoContacto;
    private String nombreSolicitante;
    private int disponible;

    public Adopcion(Long identificacionHuerfano, Long telefonoContacto, String nombreSolicitante, int disponible) {
        this.identificacionHuerfano = identificacionHuerfano;
        this.telefonoContacto = telefonoContacto;
        this.nombreSolicitante = nombreSolicitante;
        this.disponible = disponible;
    }

    public Adopcion() {

    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public Long getIdentificacionHuerfano() {
        return identificacionHuerfano;
    }

    public void setIdentificacionHuerfano(Long identificacionHuerfano) {
        this.identificacionHuerfano = identificacionHuerfano;
    }

    public Long getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    @Override
    public String toString() {
        return "Adopcion{" +
                "identificacionHuerfano=" + identificacionHuerfano +
                ", telefonoContacto=" + telefonoContacto +
                ", nombreSolicitante='" + nombreSolicitante + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
