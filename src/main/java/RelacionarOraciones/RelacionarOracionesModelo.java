package RelacionarOraciones;

public class RelacionarOracionesModelo {
    private String oracion;
    private String respuesta;
    private String id_Video;

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getId_Video() {
        return id_Video;
    }

    public void setId_Video(String id_Video) {
        this.id_Video = id_Video;
    }

    @Override
    public String toString() {
        return "RelacionarOracionesModelo{" +
                "oracion='" + oracion + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", id_Video='" + id_Video + '\'' +
                '}';
    }
}
