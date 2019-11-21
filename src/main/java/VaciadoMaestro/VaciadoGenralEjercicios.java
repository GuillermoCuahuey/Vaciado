package VaciadoMaestro;

import CompletarOracion.CompletarOracionVaciado;
import JDBC.Todas_BD;
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
        Todas_BD baseDato = new Todas_BD();
        GlosarioVaciado glosarioVaciado = new GlosarioVaciado();
        ActvidadVaciado actvidadVaciado = new ActvidadVaciado();
//        actvidadVaciado.leerArchivo();
//        actvidadVaciado.llenaModelo();
//        actvidadVaciado.inserta(baseDato.conectaPostgreDigitalPruebas());
//        glosarioVaciado.leerArchivo();
//        glosarioVaciado.llenaModelo();
//        glosarioVaciado.insertaGlosario(baseDato.conectaPostgreDigitalPruebas());
//        glosarioVaciado.insertaGlosarioActividad(baseDato.conectaPostgreDigitalPruebas());
//        CompletarOracionVaciado completarOracionVaciado = new CompletarOracionVaciado();
//        completarOracionVaciado.leerArchivo();
//        completarOracionVaciado.llenaModelo();
//        completarOracionVaciado.insertarCompletarOracion(baseDato.conectaPostgreDigitalPruebas());
//        HablarVaciado hablarVaciado = new HablarVaciado();
//        hablarVaciado.leerArchivo();
//        hablarVaciado.llenaModelo();
//        hablarVaciado.insertarHablar(baseDato.conectaPostgreDigitalPruebas());
//        MapaMentalVaciado mapaMentalVaciado = new MapaMentalVaciado();
//        mapaMentalVaciado.leerArchivo();
//        mapaMentalVaciado.llenaModelo();
//        mapaMentalVaciado.insertaRelacion(baseDato.conectaPostgreDigitalPruebas());
        //mapaMentalVaciado.insertaRelacionActividadPalabrasRepetidas(mapaMentalVaciado.conectaPostgre());
//        OracionesVaciado oracionesVaciado = new OracionesVaciado();
//        oracionesVaciado.leerArchivo();
//        oracionesVaciado.llenaModelo();
//        oracionesVaciado.inserta(baseDato.conectaPostgreDigitalPruebas());
//        OrdenarPalabras ordenarPalabras = new OrdenarPalabras();
//        ordenarPalabras.leerArchivo();
//        ordenarPalabras.llenModelo();
//        ordenarPalabras.inserta(baseDato.conectaPostgreDigitalPruebas());
//        RelacionarVaciado relacionarVaciado = new RelacionarVaciado();
        //relacionarVaciado.leerArchivo();
        //relacionarVaciado.llenaModelo();
        //relacionarVaciado.insertaRelacion(baseDato.conectaPostgreDigitalPruebas());
        //relacionarVaciado.insertaRelacionActividadPalabrasRepetidas(baseDato.conectaPostgreDigitalPruebas());
        RelacionarOracionesVaciado relacionarOracionesVaciado= new RelacionarOracionesVaciado();
        relacionarOracionesVaciado.leerArchivo();
        relacionarOracionesVaciado.llenarModelo();
        relacionarOracionesVaciado.insertarRelacionarOraciones(baseDato.conectaPostgreDigitalPruebas());
    }
}
