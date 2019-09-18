package VaciadoMaestro;

import CompletarOracion.CompletarOracionVaciado;
import MapaMental.MapaMentalVaciado;
import Relacionar.RelacionarVaciado;
import RelacionarOraciones.RelacionarOracionesVaciado;
import actividad.ActvidadVaciado;
import glosario.GlosarioVaciado;
import hablar.HablarVaciado;
import oracion.OracionesVaciado;
import ordenarpalabra.OrdenarPalabras;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class VaciadoGenralEjercicios {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        GlosarioVaciado glosarioVaciado = new GlosarioVaciado();
        ActvidadVaciado actvidadVaciado = new ActvidadVaciado();
        actvidadVaciado.leerArchivo();
        actvidadVaciado.llenaModelo();
        //actvidadVaciado.inserta(actvidadVaciado.conectaPostgre());
        glosarioVaciado.leerArchivo();
        glosarioVaciado.llenaModelo();
        //glosarioVaciado.insertaGlosario(glosarioVaciado.conectaPostgre());
        //glosarioVaciado.insertaGlosarioActividad(glosarioVaciado.conectaPostgre());
        CompletarOracionVaciado completarOracionVaciado = new CompletarOracionVaciado();
        completarOracionVaciado.leerArchivo();
        completarOracionVaciado.llenaModelo();
        //completarOracionVaciado.insertarCompletarOracion(completarOracionVaciado.conectaPostgre());
        HablarVaciado hablarVaciado = new HablarVaciado();
        hablarVaciado.leerArchivo();
        hablarVaciado.llenaModelo();
        //hablarVaciado.insertarHablar(hablarVaciado.conectaPostgre());
        MapaMentalVaciado mapaMentalVaciado = new MapaMentalVaciado();
        mapaMentalVaciado.leerArchivo();
        mapaMentalVaciado.llenaModelo();
        //mapaMentalVaciado.insertaRelacion(mapaMentalVaciado.conectaPostgre());
        //mapaMentalVaciado.insertaRelacionActividadPalabrasRepetidas(mapaMentalVaciado.conectaPostgre());
        OracionesVaciado oracionesVaciado = new OracionesVaciado();
        oracionesVaciado.leerArchivo();
        oracionesVaciado.llenaModelo();
        //oracionesVaciado.inserta(oracionesVaciado.conectaPostgre());
        OrdenarPalabras ordenarPalabras = new OrdenarPalabras();
        ordenarPalabras.leerArchivo();
        ordenarPalabras.llenModelo();
        ordenarPalabras.inserta(ordenarPalabras.conectaPostgre());
        RelacionarVaciado relacionarVaciado = new RelacionarVaciado();
        relacionarVaciado.leerArchivo();
        relacionarVaciado.llenaModelo();
        relacionarVaciado.insertaRelacion(relacionarVaciado.conectaPostgre());
        relacionarVaciado.insertaRelacionActividadPalabrasRepetidas(relacionarVaciado.conectaPostgre());
        RelacionarOracionesVaciado relacionarOracionesVaciado= new RelacionarOracionesVaciado();
        relacionarOracionesVaciado.leerArchivo();
        relacionarOracionesVaciado.llenarModelo();
        relacionarOracionesVaciado.insertarRelacionarOraciones(relacionarOracionesVaciado.conectaPostgre());
    }
}
