package modelo.servicio;

import infraestructura.Conexion;
import modelo.entidad.Huerfano;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioHuerfano implements IHuerfano{


    @Override
    public List<Huerfano> listarHuerfanos() {
        List<Huerfano> huerfanos = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.conenection.prepareStatement("SELECT * FROM huerfano");
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                huerfanos.add(new Huerfano(resultado.getLong("id"),resultado.getString("nombre"),resultado.getInt("edad")));
            }

        } catch (SQLException e) {
        }
        return huerfanos;
    }

    @Override
    public String crearHuerfano(Huerfano huerfano) {
        if(this.buscarHuerfano(huerfano.getIdentificacion()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("INSERT INTO huerfano(id,nombre,edad) VALUES (?,?,?)");
                sentencia.setLong(1,huerfano.getIdentificacion());
                sentencia.setString(2,huerfano.getNombre());
                sentencia.setInt(3,huerfano.getEdad());
                sentencia.execute();
                return "Insertado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
     return "El usuario ya se encuentra registrado";


    }

    @Override
    public Optional<Huerfano> buscarHuerfano(Long identificacion) {
        List<Huerfano> huerfanos= this.listarHuerfanos();
        return huerfanos.stream().filter(huerfano -> huerfano.getIdentificacion().equals(identificacion)).findFirst();
    }

    @Override
    public String actualizarHuerfano(Huerfano huerfano) {

        if(!this.buscarHuerfano(huerfano.getIdentificacion()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("UPDATE huerfano SET nombre = ?, edad = ? WHERE id = ?");
                sentencia.setString(1,huerfano.getNombre());
                sentencia.setInt(2,huerfano.getEdad());
                sentencia.setLong(3,huerfano.getIdentificacion());
                sentencia.executeUpdate();
                return "Actualizado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El huerfano no se encuentra registrado";
    }

    @Override
    public String eliminarHuerfano(Long identificacion) {

        if(!this.buscarHuerfano(identificacion).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("DELETE FROM huerfano WHERE id = ?");
                sentencia.setLong(1,identificacion);
                return (sentencia.executeUpdate() == 0) ? "No se puede borrar " : "Borrado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El huerfano no se encuentra registrado";
    }
}
