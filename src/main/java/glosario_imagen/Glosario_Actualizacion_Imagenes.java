package glosario_imagen;

import JDBC.Todas_BD;

import java.io.FileInputStream;
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

public class Glosario_Actualizacion_Imagenes {
    List<GlosarioImagenModelo> glosarioImagenModeloLista = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<String> glosarioLista = new ArrayList<>();
    List<String> glosarioActividadLista = new ArrayList<>();
    Connection conexion;


    public void insertaGlosario(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE alumno.glosario SET imagen =? " +
                "WHERE alumno.glosario.palabra=? AND alumno.glosario.id_clase_glosario=(select clave from alumno.clase_glosario where valor = ?)") ;

        for (GlosarioImagenModelo glosarioM : glosarioImagenModeloLista) {

            System.out.println("*");
            preparedStatement.setBinaryStream(1, glosarioM.getImagen());
            preparedStatement.setString(2, glosarioM.getPalabra());
            preparedStatement.setString(3, glosarioM.getClasePalabra());



            preparedStatement.executeUpdate();

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
        String palabra ="";
        String clase_palabra="";
        GlosarioImagenModelo glosarioImagenModelo = new GlosarioImagenModelo();
        for (String s: glosarioLista){
            List<String> aux = new ArrayList<>();
            switch (i){
                case 0: {
                    palabra = s;
                    glosarioImagenModelo.setPalabra(s);
                    break;
                }
                case 1: {
                    clase_palabra = s;
                    glosarioImagenModelo.setClasePalabra(s);
                    break;
                }
                case 2: {
                    try {
//                        System.out.println("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/".concat(palabra)+"_".concat(clase_palabra)+".jpg");
//                        System.out.println("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/".concat(s)+".jpg");
                        FileInputStream file = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/".concat(palabra)+"_".concat(clase_palabra)+".jpg");
//                        FileInputStream file = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/".concat(s));
                        glosarioImagenModelo.setImagen(file);
                    } catch (Exception e) {
                        FileInputStream defaul = new FileInputStream("C:/Users/Guillermo/Desktop/Ejercicios/imagenes_glosario/empty.jpg");
                        glosarioImagenModelo.setImagen(defaul);
                        System.out.println(cont);
//
                        cont =cont+1;
                    }
                    break;
                }
                case 3: {
                    glosarioImagenModelo.setSignificado(s);
                    break;
                }
                case 4: {
                    glosarioImagenModelo.setIdVideo(s);
                    break;
                }
            }
            if (i == 4){
                glosarioImagenModeloLista.add(glosarioImagenModelo);
                glosarioImagenModelo = new GlosarioImagenModelo();
            }
            i++;
            i = (i == 5)? 0 : i;
        }
        //glosarioModeloLista.forEach(System.out::println);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Glosario_Actualizacion_Imagenes glosarioVaciado = new Glosario_Actualizacion_Imagenes();
        Todas_BD baseDatos =new Todas_BD();
        glosarioVaciado.leerArchivo();
        glosarioVaciado.llenaModelo();
        glosarioVaciado.insertaGlosario(baseDatos.conectaPostgreDigitalPruebas());
        //glosarioVaciado.insertaGlosarioActividad(baseDatos.conectaPostgreDigitalPreProduccion());

    }
}
