package hablar;

import JDBC.Todas_BD;

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

public class HablarVaciado {
    List<HablarModelo> hablarModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> completarOracionLista = new ArrayList<>();
    Connection conexion;

    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/hablar.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stringList = stream.filter(line -> !line.startsWith("#"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for(String s: stringList){
                List<String> lecturaRelacionarLista = new ArrayList<>();
                lecturaRelacionarLista.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
                completarOracionLista.addAll(lecturaRelacionarLista);
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
        HablarModelo hablarModelo = new HablarModelo();
        for (String s: completarOracionLista){
            switch (i){
                case 0: {
                    hablarModelo.setOracion(s);
                    System.out.println(s);
                    break;
                }

                case 1:{
                    hablarModelo.setId_Video(s);
                    System.out.println(s);
                    break;
                }

            }
            if (i == 1){
                hablarModeloLista.add(hablarModelo);
                hablarModelo = new HablarModelo();
            }
            i++;
            i = (i == 2)? 0 : i;
        }
        //hablarModeloLista.forEach(System.out::println);
    }
    public void insertarHablar(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.hablar (id, id_actividad, tarjeta) values\n" +
                "(default ,?,?) ON CONFLICT (id) DO NOTHING");

        for (HablarModelo hablarM : hablarModeloLista) {
            System.out.println(hablarM.getId_Video());
            preparedStatement.setString(1, hablarM.getId_Video());
            preparedStatement.setString(2, hablarM.getOracion());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        HablarVaciado hablarVaciado = new HablarVaciado();
        Todas_BD baseDatos = new  Todas_BD();
        hablarVaciado.leerArchivo();
        hablarVaciado.llenaModelo();
        hablarVaciado.insertarHablar(baseDatos.conectaPostgreDigitalPreProduccion());

    }

}
