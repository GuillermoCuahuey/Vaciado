package MapaMental;

public class MapaMentalModelado {

    private String pregunta;
    private Short cardinalidad;
    private String idVideo;
    //private String ;


    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }



    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @Override
    public String toString() {
        return "MapaMentalModelado{" +
                "pregunta='" + pregunta + '\'' +
                ", cardinalidad=" + cardinalidad +
                ", idVideo='" + idVideo + '\'' +
                '}';
    }
}
