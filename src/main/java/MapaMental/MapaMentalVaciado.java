package MapaMental;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapaMentalVaciado {
    List<String> stringList = new ArrayList<>();
    List<String> mapaMentalLista = new ArrayList<>();
    List<MapaMentalModelado> mapaMentalModeloLista = new ArrayList<>();
    Connection conexion;
    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/Relacionar.csv";
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
        //stringList.forEach(System.out::println);
        //glosarioLista.forEach(System.out::println);
        //glosarioActividadLista.forEach(System.out::println);
    }
    public void llenaModelo() throws FileNotFoundException {
        int i = 0;
        MapaMentalModelado mapaMentalModelado = new MapaMentalModelado();
        for (String s: mapaMentalLista){
            switch (i){
                case 0: {
                    mapaMentalModelado.setPregunta(s);
                    //System.out.println(s);
                    break;
                }
                case 1: {
                    mapaMentalModelado.setCardinalidad(s);
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
        //palabrasRepetidasLista.forEach(System.out::println);
    }

    public Connection conectaPostgre() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://raja.db.elephantsql.com:5432/pqnjegbu?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pqnjegbu", "PxMi0zXcr2vynTFNE_KHPIrzKbLKzIfU");
        return conexion;
    }



}
