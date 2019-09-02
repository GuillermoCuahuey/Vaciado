package Relacionar;

import java.io.InputStream;

public class RelacionarModelo {

    private String palabra;
    private InputStream imagen;
    private String idVideo;

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    @Override
    public String toString() {
        return "RelacionarModelo{" +
                "palabra='" + palabra + '\'' +
                ", imagen=" + imagen +
                ", idVideo='" + idVideo + '\'' +
                '}';
    }
}
