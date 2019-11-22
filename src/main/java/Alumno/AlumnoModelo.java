package Alumno;

import java.sql.Date;

public class AlumnoModelo {
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String apodo;
    private String contrasenia;
    private String sexo;
    private Integer id;
    private  Integer id_nivel_lenguaje;
    private Date nacimiento;
    private String correo_electronico;
    private String contrasenia_padre;

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasenia_padre() {
        return contrasenia_padre;
    }

    public void setContrasenia_padre(String contrasenia_padre) {
        this.contrasenia_padre = contrasenia_padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_nivel_lenguaje() {
        return id_nivel_lenguaje;
    }

    public void setId_nivel_lenguaje(Integer id_nivel_lenguaje) {
        this.id_nivel_lenguaje = id_nivel_lenguaje;
    }
}
