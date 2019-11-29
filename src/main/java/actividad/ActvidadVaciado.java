package actividad;

import JDBC.Todas_BD;

import java.io.FileInputStream;
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

public class ActvidadVaciado {
    List<String> stringList = new ArrayList<>();
    List<String> nuevaLista = new ArrayList<>();
    List<ActividaModelo> actividaModeloList = new ArrayList<>();
    Connection conexion;


    public void inserta(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into alumno.actividad (id_video, puntaje, id_tipo_estudiante, tiempo, pregunta_detonadora, lenguaje, transcripcion, id_tema, vista_previa)\n" +
                "values (?, 100, (select clave from alumno.tipo_estudiante where valor = ?), ?, ?, ?, ?, (select clave from alumno.tema where valor= ?),?)");

        PreparedStatement preparedStatementLLevelLanguaje = connection.prepareStatement(
                "INSERT INTO  alumno.nivel_lenguaje_actividad (id_actividad, id_nivel_lenguaje) VALUES (?, (SELECT clave FROM alumno.nivel_lenguaje WHERE valor = ?))");
        //preparedStatement.setString(1, actividaModelo.getIdVideo());
        for (ActividaModelo actividadM : actividaModeloList) {
            preparedStatement.setString(1, actividadM.getIdVideo());
            preparedStatement.setString(2, actividadM.getTipoEstudiante());
            preparedStatement.setInt(3, Integer.parseInt(actividadM.getTiempo()));
            preparedStatement.setString(4, actividadM.getPregunta());
            preparedStatement.setString(5, actividadM.getLenguaje());
            preparedStatement.setString(6, actividadM.getTranscript());
            preparedStatement.setString(7, actividadM.getTema());
            preparedStatement.setBinaryStream(8, actividadM.getVistaPrevia());
            //Agregar la pregunta detonadora para la insercion en la base de datos.
            preparedStatement.executeUpdate();

            for(String nivelLenguaje : actividadM.getNivelLenguaje()) {
                preparedStatementLLevelLanguaje.setString(1, actividadM.getIdVideo());
                preparedStatementLLevelLanguaje.setString(2, nivelLenguaje);
                preparedStatementLLevelLanguaje.executeUpdate();
            }
        }
        connection.close();
    }
    public void leerArchivo(){
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/transcripcion.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stringList = stream.filter(line -> !line.startsWith("título"))
                    .map(String::toString)
                    .collect(Collectors.toList());
            for (String s : stringList) {
                nuevaLista.addAll(Pattern.compile("\\|")
                        .splitAsStream(s)
                        .collect(Collectors.toList()));
            }

        }catch(IOException e){
            System.out.println("Ocurió un error: " + e.getCause() +"\nMensaje: "+ e.getMessage());
        }
        //stringList.forEach(System.out::println);
        //nuevaLista.forEach(System.out::println);
    }
    public void llenaModelo(){
        int i = 0;
        ActividaModelo actividaModelo = new ActividaModelo();
        for (String s: nuevaLista){
            List<String> aux = new ArrayList<>();
            switch (i){
                case 0: {}
                case 1: {
                    if(s.matches("(.*)&(.*)")){
                        aux.addAll(Pattern.compile("&").splitAsStream(s).collect(Collectors.toList()));
                    }else{
                        aux.add(s);
                    }
                    actividaModelo.setNivelLenguaje(aux);
                    break;
                }
                case 2: {
                    actividaModelo.setTipoEstudiante(s);
                    break;
                }
                case 3: {
                    actividaModelo.setTiempo(s);
                    break;
                }
                case 4: {
                    actividaModelo.setTema(s);
                    break;
                }
                case 5: {
                    actividaModelo.setLenguaje(s);
                    break;
                }
                case 6:{
                    actividaModelo.setPregunta(s);
                    break;
                }
                case 7: {
                    actividaModelo.setIdVideo(s);
                    try{
                        FileInputStream file = new FileInputStream("C:/Users/Antonio/Desktop/imagenes_actividad/".concat(s).concat(".jpg"));
                        actividaModelo.setVistaPrevia(file);
                    }catch (Exception e){
                        System.out.println("Ocurrió un error al abrir el archivo: "+e.getMessage());
                    }
                    break;
                }
                case 8: {
                    actividaModelo.setTranscript(s);
                    break;
                }
            }
            if (i == 8){
                actividaModeloList.add(actividaModelo);
                actividaModelo = new ActividaModelo();
            }
            i++;
            i = (i == 9)? 0 : i;
        }
        System.out.println("Resultado&: "+ i + nuevaLista.size());
        actividaModeloList.forEach(System.out::println);

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ActvidadVaciado actvidadVaciado = new ActvidadVaciado();
        Todas_BD baseDatos = new Todas_BD();
        actvidadVaciado.leerArchivo();
        actvidadVaciado.llenaModelo();
        //
        actvidadVaciado.inserta(baseDatos.conectaPostgreDigitalPreProduccion());
    }
}
