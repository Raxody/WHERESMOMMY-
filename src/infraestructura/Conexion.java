package infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Conexion {
    private static String user = "postgres";
    private static String password = "root";
    private static  String databaseName = "wheresmommy";
    public static Connection conenection;

    public static void iniciarConexion(){
        try{
            conenection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+databaseName, user,password);
        }catch (Exception e){
        }
    }




}
