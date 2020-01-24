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
        actvidadVaciado.inserta(baseDato.conectaPostgreDigitalPruebas());
        glosarioVaciado.leerArchivo();
        glosarioVaciado.llenaModelo();
        glosarioVaciado.insertaGlosario(baseDato.conectaPostgreDigitalPruebas());

        CompletarOracionVaciado completarOracionVaciado = new CompletarOracionVaciado();
        completarOracionVaciado.leerArchivo();
        completarOracionVaciado.llenaModelo();
        completarOracionVaciado.insertarCompletarOracion(baseDato.conectaPostgreDigitalPruebas());
        HablarVaciado hablarVaciado = new HablarVaciado();
        hablarVaciado.leerArchivo();
        hablarVaciado.llenaModelo();
        hablarVaciado.insertarHablar(baseDato.conectaPostgreDigitalPruebas());
       MapaMentalVaciado mapaMentalVaciado = new MapaMentalVaciado();
        mapaMentalVaciado.leerArchivo();
        mapaMentalVaciado.llenaModelo();
        mapaMentalVaciado.insertaRelacion(baseDato.conectaPostgreDigitalPruebas());
        OracionesVaciado oracionesVaciado = new OracionesVaciado();
        oracionesVaciado.leerArchivo();
        oracionesVaciado.llenaModelo();
        oracionesVaciado.inserta(baseDato.conectaPostgreDigitalPruebas());
        OrdenarPalabras ordenarPalabras = new OrdenarPalabras();
        ordenarPalabras.leerArchivo();
        ordenarPalabras.llenModelo();
        ordenarPalabras.inserta(baseDato.conectaPostgreDigitalPruebas());
        RelacionarOracionesVaciado relacionarOracionesVaciado= new RelacionarOracionesVaciado();
        relacionarOracionesVaciado.leerArchivo();
        relacionarOracionesVaciado.llenarModelo();
        relacionarOracionesVaciado.insertarRelacionarOraciones(baseDato.conectaPostgreDigitalPruebas()
                );
    }
}
