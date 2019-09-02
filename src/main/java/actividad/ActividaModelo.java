package actividad;

import java.util.List;
import java.util.StringJoiner;

public class ActividaModelo {
    private List<String> nivelLenguaje;
    private String tipoEstudiante;
    private String pregunta;
    private String lenguaje;
    private String transcript;
    private String tema;
    private String idVideo;
    private String tiempo;

    public ActividaModelo() {
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public List<String> getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(List<String> nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    public String getTipoEstudiante() {
        return tipoEstudiante;
    }

    public void setTipoEstudiante(String tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActividaModelo.class.getSimpleName() + "[", "]")
                .add("nivelLenguaje=" + nivelLenguaje)
                .add("tipoEstudiante='" + tipoEstudiante + "'")
                .add("pregunta='" + pregunta + "'")
                .add("lenguaje='" + lenguaje + "'")
                .add("transcript='" + transcript + "'")
                .add("tema='" + tema + "'")
                .add("idVideo='" + idVideo + "'")
                .add("tiempo='" + tiempo + "'")
                .toString();
    }
}
