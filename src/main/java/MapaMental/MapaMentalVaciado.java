package MapaMental;
import JDBC.Todas_BD;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Short.parseShort;

public class MapaMentalVaciado {
    List<String> stringList = new ArrayList<>();
    List<String> mapaMentalLista = new ArrayList<>();
    List<MapaMentalModelado> mapaMentalModeloLista = new ArrayList<>();

    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/mapa_mental.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stringList = stream.filter(line -> !line.startsWith("#"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for(String s: stringList){
                List<String> lecturaRelacionarLista = new ArrayList<>();
                lecturaRelacionarLista.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
                mapaMentalLista.addAll(lecturaRelacionarLista);
                //System.out.println("leerArchivo: ");
            }
        }catch(Exception ioe){
            System.out.println("Ocurrio un error: "+ioe.getCause()+"\nMensaje: "+ioe.getMessage());
        }
        mapaMentalLista.forEach(System.out::println);
        //glosarioLista.forEach(System.out::println);
        //glosarioActividadLista.forEach(System.out::println);
    }
    public void llenaModelo() throws FileNotFoundException {
        int i = 0;
        MapaMentalModelado mapaMentalModelado = new MapaMentalModelado();
        //System.out.println(mapaMentalLista);
        for (String s: mapaMentalLista){
            switch (i){
                case 0: {

                    mapaMentalModelado.setCardinalidad(Short.parseShort(s));
                    break;
                }
                case 1: {
                    mapaMentalModelado.setPregunta(s);
                    //System.out.println(s);

                    break;
                }
                case 2:{
                    mapaMentalModelado.setIdVideo(s);
                    //System.out.println(s);
                    break;
                }

            }
            if (i == 2){
                mapaMentalModeloLista.add(mapaMentalModelado);
                mapaMentalModelado = new MapaMentalModelado();
            }
            i++;
            i = (i == 3)? 0 : i;
        }
    }
    public void insertaRelacion(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into alumno.mapamental (pregunta,cardinalidad,codigo) values (?,?,default ) ON CONFLICT (codigo, cardinalidad) DO NOTHING;");
        PreparedStatement preparedStatementRelacionarActividad = connection.prepareStatement(
                "insert into alumno.mapamental_actividad (codigo,cardinalidad,id_actividad) values ( (SELECT codigo FROM alumno.mapamental WHERE cardinalidad = ? AND pregunta = ?),?,?)  ON CONFLICT (codigo,cardinalidad,id_actividad) DO NOTHING");

        for (MapaMentalModelado mapaM : mapaMentalModeloLista) {
            preparedStatement.setString(1, mapaM.getPregunta());
            preparedStatement.setShort(2, mapaM.getCardinalidad());
            preparedStatement.executeUpdate();
            System.out.println();
            preparedStatementRelacionarActividad.setShort(1,mapaM.getCardinalidad());
            preparedStatementRelacionarActividad.setString(2, mapaM.getPregunta());
            preparedStatementRelacionarActividad.setShort(3,mapaM.getCardinalidad());
            preparedStatementRelacionarActividad.setString(4,mapaM.getIdVideo());
            preparedStatementRelacionarActividad.executeUpdate();
        }
//        connection.commit();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Todas_BD baseDato = new Todas_BD();
        MapaMentalVaciado mapaMentalVaciado = new MapaMentalVaciado();
        mapaMentalVaciado.leerArchivo();
        mapaMentalVaciado.llenaModelo();
        mapaMentalVaciado.insertaRelacion(baseDato.conectaPostgreDigitalPruebas());
        //mapaMentalVaciado.insertaRelacionActividadPalabrasRepetidas(mapaMentalVaciado.conectaPostgre());
    }

}
