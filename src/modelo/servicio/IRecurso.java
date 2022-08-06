package modelo.servicio;

import modelo.entidad.Recursos;

import java.util.List;
import java.util.Optional;

public interface IRecurso {

    public List<Recursos> listarRecursos();
    public String crearRecursos(Recursos recursos);
    public Optional<Recursos> buscarRecursos(String nombre);
    public String actualizarRecursos(Recursos recursos);
    public String eliminarRecursos(String nombre);
}
