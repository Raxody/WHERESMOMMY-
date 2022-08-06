import controlador.Controlador;
import infraestructura.Conexion;
import jdk.swing.interop.SwingInterOpUtils;
import modelo.entidad.Adopcion;
import modelo.entidad.Funcionario;
import modelo.entidad.Huerfano;
import modelo.entidad.Recursos;
import modelo.servicio.*;

import javax.swing.*;

public class Principal {

    public static void main(String[] args) {

       Conexion.iniciarConexion();

        Controlador controlador = new Controlador();

        controlador.iniciar();
/*
        IRecurso recurso = new ServicioRecurso();

        System.out.println(recurso.crearRecursos(new Recursos("lapices",200)));;

        System.out.println(recurso.actualizarRecursos(new Recursos("sillassss",20)));;

        recurso.listarRecursos().forEach(adopcion -> System.out.println(adopcion.toString()));


        System.out.println(recurso.buscarRecursos("lapices").toString());

        System.out.println(recurso.eliminarRecursos("lapices"));

        IFuncionarios funcionarios = new ServicioFuncionario();

        System.out.println(funcionarios.crearFuncionarios(new Funcionario(1234567892L,"Andres","Profesor",25)));;


        System.out.println(funcionarios.actualizarFuncionarios(new Funcionario(1234567892L,"Andresito","Profesorcito",26)));;

        System.out.println(funcionarios.eliminarFuncionarios(1234567892L));

        funcionarios.listarFuncionarios().forEach(adopcion -> System.out.println(adopcion.toString()));*/


    }
}
