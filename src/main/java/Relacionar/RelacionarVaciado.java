package Relacionar;

import JDBC.Todas_BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

public class RelacionarVaciado {

    List<RelacionarModelo> relacionarModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> relacionarLista = new ArrayList<>();
    List<String> palabrasRepetidasLista = new ArrayList<>();
    Connection conexion;

    public Connection conectaPostgre() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://raja.db.elephantsql.com:5432/pqnjegbu?useServerPrepStmts=true";
        conexion =  DriverManager.getConnection(url, "pqnjegbu", "PxMi0zXcr2vynTFNE_KHPIrzKbLKzIfU");
        return conexion;
    }
//    public Connection conectaPostgre() throws ClassNotFoundException, SQLException {
//        Class.forName("org.postgresql.Driver");
//        String url = "jdbc:postgresql://e-squadron.com.mx:3693/tecolotlpruebasdb?useServerPrepStmts=true";
//        conexion =  DriverManager.getConnection(url, "pruebastecolotl", "f78xi1Czu20");
//        return conexion;
//    }
    public void insertaRelacion(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.relacionar (palabra,imagen) values\n" +
                "(?,?)");
        PreparedStatement preparedStatementRelacionarActividad = connection.prepareStatement(
                "insert into alumno.actividad_relacionar (id_relacionar,id_actividad) values" +"((SELECT codigo FROM alumno.relacionar WHERE palabra = ?),?)");

        for (RelacionarModelo relacionM : relacionarModeloLista) {
            preparedStatement.setString(1, relacionM.getPalabra());
            preparedStatement.setBinaryStream(2, relacionM.getImagen());
            preparedStatement.executeUpdate();
            preparedStatementRelacionarActividad.setString(1,relacionM.getPalabra());
            preparedStatementRelacionarActividad.setString(2,relacionM.getIdVideo());
            preparedStatementRelacionarActividad.executeUpdate();
        }
        connection.close();
    }
    public void insertaRelacionActividadPalabrasRepetidas(Connection connection)throws SQLException{
        PreparedStatement preparedStatement= connection.prepareStatement("insert into alumno.actividad_relacionar (id_relacionar,id_actividad) values" +"((SELECT codigo FROM alumno.relacionar WHERE palabra = ?),?)"

        );
        int i=0;
        for (String s : palabrasRepetidasLista){
            if((i % 2) == 0){
                System.out.println(" 1 "+s);
                preparedStatement.setString(1, s);
                System.out.println("2"+palabrasRepetidasLista.get(i+1));
                preparedStatement.setString(2, palabrasRepetidasLista.get(i+1));
                preparedStatement.executeUpdate();
            }
            i++;
        }
        connection.close();
    }
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
                if (lecturaRelacionarLista.size() > 2) {
                    relacionarLista.addAll(lecturaRelacionarLista);
                }else {
                    palabrasRepetidasLista.addAll(lecturaRelacionarLista);
                }

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
        RelacionarModelo relacionarModelo = new RelacionarModelo();
        for (String s: relacionarLista){
            switch (i){
                case 0: {
                    relacionarModelo.setPalabra(s);
                    //System.out.println(s);
                    break;
                }
                case 1: {

//                    try {
                        FileInputStream file = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_relacionar/".concat(s));
                        relacionarModelo.setImagen(file);
                        //System.out.println(s);
//                    }catch (Exception e){
//                        FileInputStream file = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_relacionar/empty.jpg");
//                        relacionarModelo.setImagen(file);
//
//                    }
                    break;
                    }
                case 2:{
                        relacionarModelo.setIdVideo(s);
                    //System.out.println(s);
                    break;
                }

            }
            if (i == 2){
                relacionarModeloLista.add(relacionarModelo);
                relacionarModelo = new RelacionarModelo();
            }
            i++;
            i = (i == 3)? 0 : i;
        }
        palabrasRepetidasLista.forEach(System.out::println);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        RelacionarVaciado relacionarVaciado = new RelacionarVaciado();
        Todas_BD baseDatos =new Todas_BD();
        relacionarVaciado.leerArchivo();
        relacionarVaciado.llenaModelo();
        relacionarVaciado.insertaRelacion(baseDatos.conectaPostgrePruebas());
        relacionarVaciado.insertaRelacionActividadPalabrasRepetidas(baseDatos.conectaPostgrePruebas());

    }

}
