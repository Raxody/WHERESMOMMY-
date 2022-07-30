package modelo.servicio;

import modelo.entidad.Huerfano;

import java.util.List;
import java.util.Optional;

public interface IHuerfano {

    public List<Huerfano> listarHuerfanos();
    public String crearHuerfano(Huerfano huerfano);
    public Optional<Huerfano> buscarHuerfano(Long identificacion);
    public String actualizarHuerfano(Huerfano huerfano);
    public String eliminarHuerfano(Long identificacion);

}
