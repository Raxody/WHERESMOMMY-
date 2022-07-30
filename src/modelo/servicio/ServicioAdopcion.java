package modelo.servicio;

import infraestructura.Conexion;
import jdk.swing.interop.SwingInterOpUtils;
import modelo.entidad.Adopcion;
import modelo.entidad.Huerfano;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioAdopcion implements IAdopcion {

    @Override
    public List<Adopcion> listarAdopcion() {
        List<Adopcion> adopcion = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.conenection.prepareStatement("SELECT * FROM solicitudAdopcion");
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                adopcion.add(new Adopcion(resultado.getLong("identificacionHuerfano"),
                        resultado.getLong("telefonoContacto"),
                        resultado.getString("nombreSolicitante"), resultado.getInt("aprobar")));
            }
        } catch (SQLException e) {
        }
        return adopcion;
    }

    @Override
    public String aceptarSolicitudAdopcion(Adopcion adopcion) {
        if (!this.buscarAdopcion(adopcion.getIdentificacionHuerfano(), adopcion.getTelefonoContacto()).isEmpty()) {
            ServicioHuerfano servicioHuerfano = new ServicioHuerfano();
            servicioHuerfano.eliminarHuerfano(adopcion.getIdentificacionHuerfano());
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("DELETE FROM solicitudAdopcion WHERE identificacionHuerfano = ?");
                sentencia.setLong(1, adopcion.getIdentificacionHuerfano());
                return (sentencia.executeUpdate() == 0) ? "No se pudo aceptar " : "Felicitaciones por tu nuevo hijo!";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El huerfano no se encuentra registrado";
    }

    @Override
    public String generarSolicitudAdopcion(Adopcion adopcion) {
        try {
            PreparedStatement sentencia = Conexion.conenection.prepareStatement("INSERT INTO solicitudAdopcion(identificacionHuerfano,telefonoContacto,nombreSolicitante,aprobar) VALUES (?,?,?,?)");
            sentencia.setLong(1, adopcion.getIdentificacionHuerfano());
            sentencia.setLong(2, adopcion.getTelefonoContacto());
            sentencia.setString(3, adopcion.getNombreSolicitante());
            sentencia.setInt(4, adopcion.getDisponible());
            sentencia.execute();
            return "Solicitud generada con exito";
        } catch (SQLException e) {
            return "Solicitud creadacon anterioridad, por favor espera ";
        }
    }

    @Override
    public Optional<Adopcion> buscarAdopcion(Long identificacion, Long telefonoContacto) {
        List<Adopcion> adopcionEncontrada = this.listarAdopcion();
        return adopcionEncontrada.stream().filter(adopcion ->  adopcion.getDisponible() == 2
                && adopcion.getIdentificacionHuerfano().equals(identificacion) && adopcion.getTelefonoContacto().equals(telefonoContacto)
        ).findFirst();
    }

    @Override
    public String actualizarAdopcion(Adopcion adopcion) {
        if(!this.buscarAdopcion(adopcion.getIdentificacionHuerfano(),adopcion.getTelefonoContacto()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("UPDATE solicitudAdopcion SET nombreSolicitante = ? WHERE identificacionHuerfano = ? AND telefonoContacto = ?");
                sentencia.setString(1,adopcion.getNombreSolicitante());
                sentencia.setLong(2,adopcion.getIdentificacionHuerfano());
                sentencia.setLong(3,adopcion.getTelefonoContacto());
                sentencia.executeUpdate();
                return "Actualizado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "La adopcion no se encuentra registrada";
    }

    @Override
    public String eliminarAdopcion(Long identificacion, Long telefonoContacto) {
        if (!this.buscarAdopcion(identificacion, telefonoContacto).isEmpty()) {
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("DELETE FROM solicitudAdopcion WHERE identificacionHuerfano = ? AND telefonoContacto = ?");
                sentencia.setLong(1, identificacion);
                sentencia.setLong(2, telefonoContacto);
                return (sentencia.executeUpdate() == 0) ? "No se puede borrar " : "Borrado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "Los datos no coinciden";
    }
}
