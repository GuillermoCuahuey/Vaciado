package RelacionarOraciones;

import JDBC.Todas_BD;
import hablar.HablarModelo;

import java.io.FileNotFoundException;
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

public class RelacionarOracionesVaciado {

    List<RelacionarOracionesModelo> relacionarOracionesModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> relacionarOracionesLista = new ArrayList<>();
    Connection conexion;


    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/mach.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stringList = stream.filter(line -> !line.startsWith("#"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for(String s: stringList){
                List<String> lecturaRelacionarLista = new ArrayList<>();
                lecturaRelacionarLista.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
                relacionarOracionesLista.addAll(lecturaRelacionarLista);
                //System.out.println("leerArchivo: ");
            }
        }catch(Exception ioe){
            System.out.println("Ocurrio un error: "+ioe.getCause()+"\nMensaje: "+ioe.getMessage());
        }
        //stringList.forEach(System.out::println);
        //glosarioLista.forEach(System.out::println);
        //glosarioActividadLista.forEach(System.out::println);
    }
    public void llenarModelo() throws FileNotFoundException {
        int i = 0;
        RelacionarOracionesModelo relacionarOracionesModelo= new RelacionarOracionesModelo();
        for (String s: relacionarOracionesLista){
            switch (i){
                case 0: {
                    relacionarOracionesModelo.setOracion(s);
                    //System.out.println(s);
                    break;
                }
                case 1: {
                    relacionarOracionesModelo.setRespuesta(s);

                    //System.out.println(s);

                    break;
                }
                case 2:{
                    relacionarOracionesModelo.setId_Video(s);
                    //System.out.println(s);
                    break;
                }

            }
            if (i == 2){
                relacionarOracionesModeloLista.add(relacionarOracionesModelo);
                relacionarOracionesModelo = new RelacionarOracionesModelo();
            }
            i++;
            i = (i == 3)? 0 : i;
        }
        relacionarOracionesModeloLista.forEach(System.out::println);
    }
    public void insertarRelacionarOraciones(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.relacionar_oracion (id,pregunta, respuesta, id_actividad) values\n" +
                "(default,?,?,?)");

        for (RelacionarOracionesModelo relacionarOracionesM : relacionarOracionesModeloLista) {
            preparedStatement.setString(1, relacionarOracionesM.getOracion());
            preparedStatement.setString(2, relacionarOracionesM.getRespuesta());
            preparedStatement.setString(3, relacionarOracionesM.getId_Video());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Todas_BD baseDato = new Todas_BD();
        RelacionarOracionesVaciado  relacionarOracionesVaciado= new RelacionarOracionesVaciado();
        relacionarOracionesVaciado.leerArchivo();
        relacionarOracionesVaciado.llenarModelo();
        relacionarOracionesVaciado.insertarRelacionarOraciones(baseDato.conectaPostgreDesarrollo());


    }


}
