package modelo.servicio;

import infraestructura.Conexion;
import modelo.entidad.Funcionario;
import modelo.entidad.Huerfano;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioFuncionario implements IFuncionarios{
    @Override
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.conenection.prepareStatement("SELECT * FROM funcionario");
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                funcionarios.add(new Funcionario(resultado.getLong("id"),resultado.getString("nombre"),resultado.getString("cargo"),resultado.getInt("edad")));
            }

        } catch (SQLException e) {
        }
        return funcionarios;
    }

    @Override
    public String crearFuncionarios(Funcionario funcionarios) {
        if(this.buscarFuncionarios(funcionarios.getIdentificacion()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("INSERT INTO funcionario(id,nombre,cargo,edad) VALUES (?,?,?,?)");
                sentencia.setLong(1,funcionarios.getIdentificacion());
                sentencia.setString(2,funcionarios.getNombre());
                sentencia.setString(3,funcionarios.getCargo());
                sentencia.setInt(4,funcionarios.getEdad());
                sentencia.execute();
                return "Insertado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El funcionario ya se encuentra registrado";
    }

    @Override
    public Optional<Funcionario> buscarFuncionarios(Long identificaion) {
        List<Funcionario> funcionarios= this.listarFuncionarios();
        return funcionarios.stream().filter(huerfano -> huerfano.getIdentificacion().equals(identificaion)).findFirst();
    }

    @Override
    public String actualizarFuncionarios(Funcionario funcionarios) {
        if(!this.buscarFuncionarios(funcionarios.getIdentificacion()).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("UPDATE funcionario SET nombre = ? , edad = ? , cargo = ? WHERE id = ? ");
                sentencia.setString(1,funcionarios.getNombre());
                sentencia.setInt(2,funcionarios.getEdad());
                sentencia.setString(3,funcionarios.getCargo());
                sentencia.setLong(4,funcionarios.getIdentificacion());


                sentencia.executeUpdate();
                return "Actualizado con exito";
            } catch (SQLException e) {
                return "algo fallo "+ e.getMessage();
            }
        }
        return "El funcionario no se encuentra registrado";
    }

    @Override
    public String eliminarFuncionarios(Long identificaion) {
        if(!this.buscarFuncionarios(identificaion).isEmpty()){
            try {
                PreparedStatement sentencia = Conexion.conenection.prepareStatement("DELETE FROM funcionario WHERE id = ?");
                sentencia.setLong(1,identificaion);
                return (sentencia.executeUpdate() == 0) ? "No se puede borrar " : "Borrado con exito";
            } catch (SQLException e) {
                return "algo fallo ";
            }
        }
        return "El funcionario no se encuentra registrado";
    }
}
