package Alumno;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Contrasenia {

    private static int MAX_X = 7;
    private static int MAX_Y = 4;

    private Set<String> contraseniaConjunto;

    private int x;
    private int y;

    public Contrasenia() {
    }

    public String generarAleatorio(int numeroElemento) {
        this.contraseniaConjunto = new TreeSet<String>();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        while (contraseniaConjunto.size() < numeroElemento) {
            x = random.nextInt(MAX_X);
            y = random.nextInt(MAX_Y);
            stringBuilder.append(x).append(':').append(y);
            contraseniaConjunto.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return contraseniaConjunto.stream().collect(Collectors.joining(","));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void main(String[] args) {
        Contrasenia contrasenia = new Contrasenia();
        System.out.println(contrasenia.generarAleatorio(3));
    }
}
