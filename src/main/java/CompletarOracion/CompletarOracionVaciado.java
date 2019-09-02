package CompletarOracion;

import CompletarOracion.CompletarOracionModelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompletarOracionVaciado {
    List<CompletarOracionModelo> completarOracionModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> completarOracionLista = new ArrayList<>();
    Connection conexion;

    public Connection conectaPostgre() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://raja.db.elephantsql.com:5432/pqnjegbu?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pqnjegbu", "PxMi0zXcr2vynTFNE_KHPIrzKbLKzIfU");
        return conexion;
    }

    public void insertaGlosario(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.glosario (palabra, id_clase_glosario, imagen, significado) values\n" +
                "(?, (select clave from alumno.clase_glosario where valor = ?), ?, ?)");

        for (CompletarOracionModelo completaOracionM : completarOracionModeloLista) {
            preparedStatement.setString(1, completaOracionM.getOracion());
            //preparedStatement.setString(2, completaOracionM.getCardinalidad());
            //preparedStatement.setBinaryStream(3, completaOracionM.getId_Video());
            preparedStatement.executeUpdate();
        }
    }
    
}
