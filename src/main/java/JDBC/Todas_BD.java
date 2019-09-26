package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Todas_BD {
    Connection conexion;
    public Connection conectaPostgreDesarrollo() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://raja.db.elephantsql.com:5432/pqnjegbu?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pqnjegbu", "PxMi0zXcr2vynTFNE_KHPIrzKbLKzIfU");
        return conexion;
    }
    public Connection conectaPostgrePruebas() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://e-squadron.com.mx:3693/tecolotlpruebasdb?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pruebastecolotl", "f78xi1Czu20");
        return conexion;
    }
}
