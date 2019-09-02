package oracion;

import java.util.StringJoiner;

public class OracionesModelo {
    private String oracion;
    private String cardinalidad;
    private String idVideo;

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public String getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(String cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OracionesModelo.class.getSimpleName() + "[", "]")
                .add("oracion='" + oracion + "'")
                .add("cardinalidad='" + cardinalidad + "'")
                .add("idVideo='" + idVideo + "'")
                .toString();
    }
}
