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
                alumnoModelo.setApellido_paterno(palabras[1]);
                alumnoModelo.setApellido_materno(palabras[2]);
                alumnoModelo.setApellido_materno(palabras[3]);
                alumnoModelo.setApodo(palabras[4]);
                alumnoModelo.setContrasenia(palabras[5]);
                alumnoModelo.setSexo(palabras[6]);
                alumnoModelo.setId_nivel_lenguaje(Integer.parseInt(palabras[8]));

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
                    "valor = ?), ?, ?,?, pgp_sym_encrypt_bytea(?, 'sad7f65as7df6sa87f8asd76f87ads6fa98', 'compress-algo=0, cipher-algo=aes256'))");
            preparedStatement.setString(1, alumnoModelo.getNombre());
            preparedStatement.setString(2, alumnoModelo.getApellido_paterno());
            preparedStatement.setString(3, alumnoModelo.getApellido_materno());
            preparedStatement.setString(4, alumnoModelo.getApodo());
            preparedStatement.setString(5, alumnoModelo.getContrasenia());
            preparedStatement.setString(6, alumnoModelo.getSexo());
            preparedStatement.setInt(8, alumnoModelo.getId_nivel_lenguaje());
            preparedStatement.setDate(9, alumnoModelo.getNacimiento());
            preparedStatement.setString(10, alumnoModelo.getCorreo_electronico());
            preparedStatement.setString(11, alumnoModelo.getContrasenia_padre());

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
