package CompletarOracion;

import java.io.InputStream;

public class CompletarOracionModelo {
    private String oracion;
    private Integer cardinalidad;
    private String id_Video;

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public Integer getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Integer cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public String getId_Video() {
        return id_Video;
    }

    public void setId_Video(String id_Video) {
        this.id_Video = id_Video;
    }

    @Override
    public String toString() {
        return "CompletarOracionModelo{" +
                "oracion='" + oracion + '\'' +
                ", cardinalidad=" + cardinalidad +
                ", id_Video='" + id_Video + '\'' +
                '}';
    }
}
