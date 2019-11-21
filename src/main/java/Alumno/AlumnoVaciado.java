package Alumno;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlumnoVaciado {

    Connection connection;

    public void leerArchivo() {
        String fileName = "C:/Users/Guillermo/Desktop/Ejercicios/Completar_oracion.csv";
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stream.filter(line -> !line.startsWith("#")).forEach(linea -> {
                String[] palabras = linea.split("\\|");
                AlumnoModelo  alumnoModelo = new AlumnoModelo();
                alumnoModelo.setNombre(palabras[0]);

                inserta(alumnoModelo);
            });
        } catch(Exception ioe) {
        System.out.println("Ocurrio un error: "+ioe.getCause()+"\nMensaje: "+ioe.getMessage());
        }
    }

    public void inserta(AlumnoModelo alumnoModelo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO alumno.alumno" +
                    " (nombre, apellido_paterno, apellido_materno, apodo, contrasenia, sexo, id, id_nivel_lenguaje, nacimiento, correo_padre_familia, contrasenia_padre_familia) VALUES" +
                    " (?,?, ?, ?, pgp_sym_encrypt_bytea(?, 'sad7f65as7df6sa87f8asd76f87ads6fa98', 'compress-algo=0, cipher-algo=aes256'),?,DEFAULT,(SELECT clave FROM alumno.nivel_lenguaje WHERE " +
                    "valor = ?), ?, ?, pgp_sym_encrypt_bytea(?, 'sad7f65as7df6sa87f8asd76f87ads6fa98', 'compress-algo=0, cipher-algo=aes256'))");
            preparedStatement.setString(1, alumnoModelo.getNombre());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo cargar el query por:" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        AlumnoVaciado alumnoVaciado = new AlumnoVaciado();
        alumnoVaciado.leerArchivo();
    }

}
