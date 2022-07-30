package modelo.servicio;

import modelo.entidad.Adopcion;

import java.util.List;
import java.util.Optional;

public interface IAdopcion {

    public List<Adopcion> listarAdopcion();
    public String aceptarSolicitudAdopcion(Adopcion adopcion);
    public String generarSolicitudAdopcion(Adopcion adopcion);
    public Optional<Adopcion> buscarAdopcion(Long identificacion,Long telefonoContacto);
    public String actualizarAdopcion(Adopcion adopcion);
    public String eliminarAdopcion(Long identificacion,Long telefonoContacto);

}
