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
    public Connection conectaPostgreDigitalDesarrollo() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://nahtechnology-do-user-6032745-0.db.ondigitalocean.com:25060/defaultdb?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "doadmin", "qwbfyrzd0dt0ch4w");
        return conexion;
    }
    public Connection conectaPostgreDigitalPruebas() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://nahtechnology-do-user-6032745-0.db.ondigitalocean.com:25060/pruebasesquadron?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "adminpruebasesquadron", "LvsKyI3IW5E");
        return conexion;
    }
    public Connection conectaPostgreDigitalPreProduccion() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://nahtechnology-do-user-6032745-0.db.ondigitalocean.com:25060/preproducciontecolotl?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "adminpreproducciontecoltl", ".69i57.1823j0j7&");
        return conexion;
    }
}
