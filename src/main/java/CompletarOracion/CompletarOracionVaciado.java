package CompletarOracion;
import JDBC.Todas_BD;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletarOracionVaciado {
    List<CompletarOracionModelo> completarOracionModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> completarOracionLista = new ArrayList<>();

    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/Completar_oracion.csv";
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
        completarOracionLista.forEach(System.out::println);
        //glosarioLista.forEach(System.out::println);
        //glosarioActividadLista.forEach(System.out::println);
    }
    public void llenaModelo() throws FileNotFoundException {
        int i = 0;
        CompletarOracionModelo completarOracionModelo = new CompletarOracionModelo();
        for (String s: completarOracionLista){
            switch (i){
                case 0: {
                    completarOracionModelo.setOracion(s);
                    //System.out.println(s);
                    break;
                }
                case 1: {
                    completarOracionModelo.setCardinalidad(Short.parseShort(s));

                    //System.out.println(s);

                    break;
                }
                case 2:{
                    //System.out.println(s);
                    completarOracionModelo.setId_Video(s);

                    break;
                }

            }
            if (i == 2){
                completarOracionModeloLista.add(completarOracionModelo);
                completarOracionModelo = new CompletarOracionModelo();
            }
            i++;
            i = (i == 3)? 0 : i;
        }
        completarOracionModeloLista.forEach(System.out::println);
    }
    public void insertarCompletarOracion(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.completar (id,oracion, cardinalidad, id_actividad) values\n" +
                "(default,?,?,?)  ON CONFLICT (id) DO NOTHING ");


        for (CompletarOracionModelo completaOracionM : completarOracionModeloLista) {
            preparedStatement.setString(1, completaOracionM.getOracion());
            preparedStatement.setShort(2, completaOracionM.getCardinalidad());
            System.out.println(completaOracionM.getId_Video());
            preparedStatement.setString(3, completaOracionM.getId_Video());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Todas_BD baseDatos =new Todas_BD();
        CompletarOracionVaciado completarOracionVaciado = new CompletarOracionVaciado();
        completarOracionVaciado.leerArchivo();
        completarOracionVaciado.llenaModelo();
        completarOracionVaciado.insertarCompletarOracion(baseDatos.conectaPostgreDigitalDesarrollo());


    }


}
