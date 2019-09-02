package MapaMental;

public class MapaMentalModelado {

    private String pregunta;
    private String cardinalidad;
    private String idVideo;
    //private String ;


    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
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
        return "MapaMentalModelado{" +
                "pregunta='" + pregunta + '\'' +
                ", cardinalidad='" + cardinalidad + '\'' +
                ", idVideo='" + idVideo + '\'' +
                '}';
    }
}
