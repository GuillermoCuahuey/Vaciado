package glosario;

import JDBC.Todas_BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class GlosarioVaciado {
    List<GlosarioModelo> glosarioModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> glosarioLista = new ArrayList<>();
    List<String> glosarioActividadLista = new ArrayList<>();
    Connection conexion;


    public void insertaGlosario(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.glosario (palabra, id_clase_glosario, imagen, significado) values (?, (select clave from alumno.clase_glosario where valor = ?), ?, ?) ON CONFLICT (palabra, id_clase_glosario) DO NOTHING") ;

        for (GlosarioModelo glosarioM : glosarioModeloLista) {

            System.out.println("*");
            preparedStatement.setString(1, glosarioM.getPalabra());
            preparedStatement.setString(2, glosarioM.getClasePalabra());
            preparedStatement.setBinaryStream(3, glosarioM.getImagen());
            preparedStatement.setString(4, glosarioM.getSignificado());

            preparedStatement.executeUpdate();
            System.out.println(glosarioM.getPalabra());
        }
        PreparedStatement preparedStatementGA = connection.prepareStatement(
                "insert into alumno.glosario_actividad (id_glosario,id_clase_glosario,id_actividad) values (?,(select clave from alumno.clase_glosario where valor = ?),?) on conflict (id_glosario, id_clase_glosario, id_actividad) do nothing");

        for (GlosarioModelo glosarioM : glosarioModeloLista) {
            System.out.println("*");
            preparedStatementGA.setString(1, glosarioM.getPalabra());
            preparedStatementGA.setString(2, glosarioM.getClasePalabra());
            preparedStatementGA.setString(3, glosarioM.getIdVideo());
            preparedStatementGA.executeUpdate();
            System.out.println(glosarioM.getPalabra());
            System.out.println(glosarioM.getIdVideo());

        }
        connection.close();
    }
    public void insertaGlosarioActividad(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into alumno.glosario_actividad (id_glosario,id_clase_glosario,id_actividad) values (?,(select id_clase_glosario from alumno.glosario where palabra = ?),?)");
        int i = 0;
        for (String s : glosarioActividadLista){
            if((i % 2) == 0){
                preparedStatement.setString(1, s);
                preparedStatement.setString(2, s);
                preparedStatement.setString(3, glosarioActividadLista.get(i+1));
                preparedStatement.executeUpdate();
            }
            i++;
        }
        connection.close();
    }
    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/glosario.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stringList = stream.filter(line -> !line.startsWith("#"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for(String s: stringList){
                List<String> auxiliar = new ArrayList<>();
                auxiliar.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
                if (auxiliar.size() > 3){
                    glosarioLista.addAll(auxiliar);
                }else {
                    glosarioActividadLista.addAll(auxiliar);
                }
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
        int cont = 0;
        GlosarioModelo glosarioModelo = new GlosarioModelo();
        for (String s: glosarioLista){
            List<String> aux = new ArrayList<>();
            switch (i){
                case 0: {
                    glosarioModelo.setPalabra(s);
                    break;
                }
                case 1: {
                    glosarioModelo.setClasePalabra(s);
                    break;
                }
                case 2: {
                    try {
                        FileInputStream file = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/".concat(s));
                        glosarioModelo.setImagen(file);
                    } catch (Exception e) {
                        FileInputStream defaul = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/empty.jpg");
                        glosarioModelo.setImagen(defaul);
                    }
                    break;
                }
                case 3: {
                    glosarioModelo.setSignificado(s);
                    break;
                }
                case 4: {
                    glosarioModelo.setIdVideo(s);
                    break;
                }
            }
            if (i == 4){
                glosarioModeloLista.add(glosarioModelo);
                glosarioModelo = new GlosarioModelo();
            }
            i++;
            i = (i == 5)? 0 : i;
        }
        glosarioModeloLista.forEach(System.out::println);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        GlosarioVaciado glosarioVaciado = new GlosarioVaciado();
        Todas_BD baseDatos =new Todas_BD();
        glosarioVaciado.leerArchivo();
        glosarioVaciado.llenaModelo();
        //glosarioVaciado.insertaGlosario(baseDatos.conectaPostgreDigitalDesarrollo());
        //glosarioVaciado.insertaGlosarioActividad(baseDatos.conectaPostgreDigitalDesarrollo());

    }
}
