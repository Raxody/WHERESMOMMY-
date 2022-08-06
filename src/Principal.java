import controlador.Controlador;
import infraestructura.Conexion;

public class Principal {

    public static void main(String[] args) {

       Conexion.iniciarConexion();

        Controlador controlador = new Controlador();

        controlador.iniciar();


    }
}
