package pildoras.relacioneshibernate;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Solo comprueba la conexión con la BBDD. No forma parte de la aplicación
public class PruebasJDBC {

    public static void main(String[] args) {
        
        System.out.println("Intentando conectar...");
        
        try {
            //La siguiente línea es para evitar problemas de que el jar no encuentre el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("class superado");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PruebasJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/relaciones_hibernate?useSSL=false";
            String usuario = "root";
            String contra = "";
            
            Connection miConexion = DriverManager.getConnection( jdbcURL,  usuario,  contra);
            System.out.println("Conexión realizada");
        } catch (SQLException ex) {
            Logger.getLogger(PruebasJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
