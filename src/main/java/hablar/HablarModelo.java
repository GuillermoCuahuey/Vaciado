package hablar;

public class HablarModelo {

    private String oracion;
    private String id_Video;

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public String getId_Video() {
        return id_Video;
    }

    public void setId_Video(String id_Video) {
        this.id_Video = id_Video;
    }

    @Override
    public String toString() {
        return "HablarModelo{" +
                "oracion='" + oracion + '\'' +
                ", id_Video='" + id_Video + '\'' +
                '}';
    }
}
