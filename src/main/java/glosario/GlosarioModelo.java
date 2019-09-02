package glosario;

import java.io.InputStream;
import java.util.StringJoiner;

public class GlosarioModelo {
    private String palabra;
    private String clasePalabra;
    private InputStream imagen;
    private String significado;
    private String idVideo;

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getClasePalabra() {
        return clasePalabra;
    }

    public void setClasePalabra(String clasePalabra) {
        this.clasePalabra = clasePalabra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
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
        return new StringJoiner(", ", GlosarioModelo.class.getSimpleName() + "[", "]")
                .add("palabra='" + palabra + "'")
                .add("clasePalabra='" + clasePalabra + "'")
                .add("imagen=" + imagen)
                .add("significado='" + significado + "'")
                .add("idVideo='" + idVideo + "'")
                .toString();
    }
}
