package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionBD {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/db_practica4";
//    bd_agenda
    static String usuario = "root";
    static String password = "";
    
    protected Connection conn = null;

    public conexionBD() {
        try {
            //ESPECIFICAR EL DRIVER QUE SE VA UTILIZAR
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url,usuario,password);
            if (conn != null){
                System.out.println("Conexión OK ");
            }
            
        }catch(ClassNotFoundException e){
            System.out.println("Falta driver "+e.getMessage());
        }catch(SQLException e){
            System.out.println("Error de SQL "+e.getMessage());
        }
    }
    
    public Connection conectar()
    {
        return conn;
    }
    public void desconectar()
    {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error de SQL "+ ex.getMessage());
        }
    }
}
