package modelo.servicio;

import infraestructura.Conexion;
import modelo.entidad.Recursos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioRecurso implements IRecurso {


    @Override
    public List<Recursos> listarRecursos() {
        List<Recursos> recursos = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.conenection.prepareStatement("SELECT * FROM recursos");
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                recursos.add(new Recursos(resultado.getString("nombreRecurso"),resultado.getInt("cantidad")));
            }

        } catch (SQLException e) {
        }
        return recursos;
    }

    @Override
    public String crearRecursos(Recursos recursos) {
        if(this.buscarRecursos(recursos.getRecurso()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("INSERT INTO recursos(nombreRecurso,cantidad) VALUES (?,?)");
                sentencia.setString(1,recursos.getRecurso());
                sentencia.setInt(2,recursos.getCantidad());
                sentencia.execute();
                return "Insertado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El recurso ya se encuentra registrado";
    }

    @Override
    public Optional<Recursos> buscarRecursos(String nombre) {
        List<Recursos> recursos= this.listarRecursos();
        return recursos.stream().filter(huerfano -> huerfano.getRecurso().equals(nombre)).findFirst();
    }

    @Override
    public String actualizarRecursos(Recursos recursos) {
        if(!this.buscarRecursos(recursos.getRecurso()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("UPDATE recursos SET cantidad = ? WHERE nombreRecurso = ?");
                sentencia.setInt(1,recursos.getCantidad());
                sentencia.setString(2,recursos.getRecurso());
                sentencia.executeUpdate();
                return "Actualizado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El recurso no se encuentra registrado";
    }

    @Override
    public String eliminarRecursos(String nombre) {
        if(!this.buscarRecursos(nombre).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("DELETE FROM recursos WHERE nombreRecurso = ?");
                sentencia.setString(1,nombre);
                return (sentencia.executeUpdate() == 0) ? "No se puede borrar " : "Borrado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El recurso no se encuentra registrado";
    }
}
