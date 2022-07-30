import controlador.Controlador;
import infraestructura.Conexion;
import jdk.swing.interop.SwingInterOpUtils;
import modelo.entidad.Adopcion;
import modelo.entidad.Huerfano;
import modelo.servicio.IHuerfano;
import modelo.servicio.ServicioAdopcion;
import modelo.servicio.ServicioHuerfano;

import javax.swing.*;

public class Principal {

    public static void main(String[] args) {

       Conexion.iniciarConexion();

        Controlador controlador = new Controlador();

        controlador.iniciar();

       /* IHuerfano huerfano = new ServicioHuerfano();


        huerfano.listarHuerfanos().stream().forEach(huerfano1 -> System.out.println(huerfano1.toString()));
        System.out.println("buysvcar");
        System.out.println(huerfano.buscarHuerfano(12345546L).toString());
        System.out.println("buysvcar----------------------------");
        System.out.println(huerfano.actualizarHuerfano(new Huerfano(1234567888L,"juanitito",22)).toString());

        System.out.println("buysvcar----------------------------");
        System.out.println(huerfano.eliminarHuerfano(1234567111L));
        ServicioAdopcion servicioAdopcion = new ServicioAdopcion();
        System.out.println(servicioAdopcion.generarSolicitudAdopcion(new Adopcion(123456L,31525469678L,"FULANITO",2)));
        System.out.println("------------------------------");
        System.out.println(servicioAdopcion.actualizarAdopcion(new Adopcion(123456L,31525469678L,"FULANITO Y TAN",2)));
        System.out.println("------------------------------");
        servicioAdopcion.listarAdopcion().forEach(adopcion -> System.out.println(adopcion.toString()));
        System.out.println("------------------------------");
        //System.out.println(servicioAdopcion.eliminarAdopcion(123456L,31525469678L));
        System.out.println(servicioAdopcion.aceptarSolicitudAdopcion(new Adopcion(1234567L,31525469678L,"FULANITO Y TAN",2)));


        System.out.println("-----------dd");
        servicioAdopcion.listarAdopcion().forEach(adopcion -> System.out.println(adopcion.toString()));*/



    }
}
