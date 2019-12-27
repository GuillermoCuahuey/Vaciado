package VaciadoMaestro;

import CompletarOracion.CompletarOracionVaciado;
import Glosario.Glosario_2_0;
import JDBC.Todas_BD;
import MapaMental.MapaMentalVaciado;
import RelacionarOraciones.RelacionarOracionesVaciado;
import actividad.ActvidadVaciado;
import hablar.HablarVaciado;
import oracion.OracionesVaciado;
import ordenarpalabra.OrdenarPalabras;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class VaciadoGenralEjercicios {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Todas_BD baseDato = new Todas_BD();
        Glosario_2_0 glosarioVaciado = new Glosario_2_0();
        ActvidadVaciado actvidadVaciado = new ActvidadVaciado();
        actvidadVaciado.leerArchivo();
        actvidadVaciado.llenaModelo();
        actvidadVaciado.inserta(baseDato.conectaPostgreDigitalDefault());
        glosarioVaciado.leerArchivo();
        glosarioVaciado.llenaModelo();
        glosarioVaciado.insertaGlosario(baseDato.conectaPostgreDigitalDefault());
        CompletarOracionVaciado completarOracionVaciado = new CompletarOracionVaciado();
        completarOracionVaciado.leerArchivo();
        completarOracionVaciado.llenaModelo();
        completarOracionVaciado.insertarCompletarOracion(baseDato.conectaPostgreDigitalDefault());
        HablarVaciado hablarVaciado = new HablarVaciado();
        hablarVaciado.leerArchivo();
        hablarVaciado.llenaModelo();
        hablarVaciado.insertarHablar(baseDato.conectaPostgreDigitalDefault());
        MapaMentalVaciado mapaMentalVaciado = new MapaMentalVaciado();
        mapaMentalVaciado.leerArchivo();
        mapaMentalVaciado.llenaModelo();
        mapaMentalVaciado.insertaRelacion(baseDato.conectaPostgreDigitalDefault());
        OracionesVaciado oracionesVaciado = new OracionesVaciado();
        oracionesVaciado.leerArchivo();
        oracionesVaciado.llenaModelo();
        oracionesVaciado.inserta(baseDato.conectaPostgreDigitalDefault());
        OrdenarPalabras ordenarPalabras = new OrdenarPalabras();
        ordenarPalabras.leerArchivo();
        ordenarPalabras.llenModelo();
        ordenarPalabras.inserta(baseDato.conectaPostgreDigitalDefault());
        RelacionarOracionesVaciado relacionarOracionesVaciado= new RelacionarOracionesVaciado();
        relacionarOracionesVaciado.leerArchivo();
        relacionarOracionesVaciado.llenarModelo();
        relacionarOracionesVaciado.insertarRelacionarOraciones(baseDato.conectaPostgreDigitalDefault());
    }
}
