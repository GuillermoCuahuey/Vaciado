package oracion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OracionesVaciado {
    private List<String> oracionVaciadoList = new ArrayList<>();
    private List<String> nuevalista = new ArrayList<>();
    private List<OracionesModelo> oracionesModeloList = new ArrayList<>();
    Connection conexion;

    public Connection conectaPostgre() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://raja.db.elephantsql.com:5432/pqnjegbu?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pqnjegbu", "PxMi0zXcr2vynTFNE_KHPIrzKbLKzIfU");
        return conexion;
    }
    public void inserta(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.oraciones (codigo, cardinalidad, id_actividad, oracion) values (default, ?, ?, ?)");

        //preparedStatement.setString(1, actividaModelo.getIdVideo());
        for (OracionesModelo oracionesModelo: oracionesModeloList){
            preparedStatement.setInt(1, Short.parseShort(oracionesModelo.getCardinalidad()));
            preparedStatement.setString(2, oracionesModelo.getIdVideo());
            preparedStatement.setString(3, oracionesModelo.getOracion());
            //Agregar la pregunta detonadora para la insercion en la base de datos.
            preparedStatement.executeUpdate();
        }
    }
    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/Ordenar_oraciones.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            oracionVaciadoList = stream.filter(line -> !line.startsWith("Oracion"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for (String s : oracionVaciadoList) {
                nuevalista.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
            }

        }catch(IOException e){
            System.out.println("Ocurió un error: " + e.getCause() +"\nMensaje: "+ e.getMessage());
        }
        nuevalista.forEach(System.out::println);
    }
    public void llenaModelo(){
        OracionesModelo oracionesModelo = new OracionesModelo();
        int i = 0;
        for (String s: nuevalista){
            switch (i){
                case 0: {
                    oracionesModelo.setOracion(s);
                    break;
                }
                case 1: {
                    oracionesModelo.setCardinalidad(s);
                    break;
                }
                case 2: {
                    oracionesModelo.setIdVideo(s);
                }
            }
            if(i == 2){
                oracionesModeloList.add(oracionesModelo);
                oracionesModelo = new OracionesModelo();
            }
            i++;
            i = (i == 3)? 0 : i;
        }
        oracionesModeloList.forEach(System.out::println);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OracionesVaciado oracionesVaciado = new OracionesVaciado();
        oracionesVaciado.leerArchivo();
        oracionesVaciado.llenaModelo();
        oracionesVaciado.inserta(oracionesVaciado.conectaPostgre());
    }
}
