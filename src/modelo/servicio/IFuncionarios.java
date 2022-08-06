package modelo.servicio;

import modelo.entidad.Funcionario;

import java.util.List;
import java.util.Optional;

public interface IFuncionarios {

    public List<Funcionario> listarFuncionarios();
    public String crearFuncionarios(Funcionario funcionarios);
    public Optional<Funcionario> buscarFuncionarios(Long identificaion);
    public String actualizarFuncionarios(Funcionario funcionarios);
    public String eliminarFuncionarios(Long identificaion);
}
