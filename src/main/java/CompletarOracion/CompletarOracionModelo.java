package CompletarOracion;

import java.io.InputStream;

public class CompletarOracionModelo {
    private String oracion;
    private Short cardinalidad;
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

    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
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
