package ordenarpalabra;

import java.util.StringJoiner;

public class OrdenarPalabraModelo {
    private String idVideo;
    private String palabra;

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrdenarPalabraModelo.class.getSimpleName() + "[", "]")
                .add("idVideo='" + idVideo + "'")
                .add("palabra='" + palabra + "'")
                .toString();
    }
}
