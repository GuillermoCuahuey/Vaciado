package ordenarpalabra;

import actividad.ActividaModelo;

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

public class OrdenarPalabras {
    private List<String> ordenarPalabraLista = new ArrayList<>();
    private List<String> nuevaLista = new ArrayList<>();
    private List<OrdenarPalabraModelo> ordenarPalabraModeloList = new ArrayList<>();
    Connection conexion;

    public Connection conectaPostgre() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://raja.db.elephantsql.com:5432/pqnjegbu?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pqnjegbu", "PxMi0zXcr2vynTFNE_KHPIrzKbLKzIfU");
        return conexion;
    }

    public void inserta(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.gramatica (codigo, palabra, id_actividad) VALUES (default, ?, ?)");

        //preparedStatement.setString(1, actividaModelo.getIdVideo());
        for (OrdenarPalabraModelo ordenarPalabraModelo : ordenarPalabraModeloList) {
            preparedStatement.setString(1, ordenarPalabraModelo.getPalabra());
            preparedStatement.setString(2, ordenarPalabraModelo.getIdVideo());
            //Agregar la pregunta detonadora para la insercion en la base de datos.
            preparedStatement.executeUpdate();
        }
    }

    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/Ordenar_palabras.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            ordenarPalabraLista = stream.filter(line -> !line.startsWith("título"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for (String s : ordenarPalabraLista) {
                nuevaLista.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
            }

        }catch(IOException e){
            System.out.println("Ocurió un error: " + e.getCause() +"\nMensaje: "+ e.getMessage());
        }
         nuevaLista.forEach(System.out::println);
    }

    public void llenModelo(){
        OrdenarPalabraModelo ordenarPalabraModelo = new OrdenarPalabraModelo();
        int i = 0;
        for (String s: nuevaLista){
            switch (i){
                case 0:{
                    ordenarPalabraModelo.setPalabra(s);
                    break;
                }
                case 1:{
                    ordenarPalabraModelo.setIdVideo(s);
                    break;
                }
            }
            if(i == 1){
                ordenarPalabraModeloList.add(ordenarPalabraModelo);
                ordenarPalabraModelo = new OrdenarPalabraModelo();
            }
            i++;
            i = (i == 2)? 0: i;
        }
        ordenarPalabraModeloList.forEach(System.out::println);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrdenarPalabras ordenarPalabras = new OrdenarPalabras();
        ordenarPalabras.leerArchivo();
        ordenarPalabras.llenModelo();
        ordenarPalabras.inserta(ordenarPalabras.conectaPostgre());
    }
}
