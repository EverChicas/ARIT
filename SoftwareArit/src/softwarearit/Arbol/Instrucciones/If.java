/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class If extends Instruccion {

    LinkedList<Instruccion> listaCondiciones;
    Instruccion bloqueElse; //BLOQUE DE INSTRUCCIONES DEL ELSE

    public If(LinkedList<Instruccion> condiciones) {
        this.listaCondiciones = condiciones;
        this.bloqueElse = null;
        generarGrafica();
    }

    public If(LinkedList<Instruccion> condiciones, Instruccion BloqueElse) {
        this.listaCondiciones = condiciones;
        this.bloqueElse = BloqueElse;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        LinkedList<Instruccion> hijos = new LinkedList();
        hijos = (LinkedList)this.listaCondiciones.clone();

        if (this.bloqueElse != null) {
            this.bloqueElse.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
            this.bloqueElse.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("Bloque else", this.bloqueElse, ((Bloque) this.bloqueElse).instrucciones);
            hijos.add(this.bloqueElse);
        }

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("if", this, hijos);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        for (Instruccion condicionIf : this.listaCondiciones) {
            Object resultIf = condicionIf.Ejecutar(e);
            if (((BloqueIf) condicionIf).condificionCumplida) {
                if (resultIf != null) {
                    if (resultIf instanceof Break) {
                        return resultIf;
                    } else if (resultIf instanceof Continue) {
                        return resultIf;
                    } else if (resultIf instanceof Return) {
                        return resultIf;
                    } else {
                        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en la salida del if", LINEA, COLUMNA));
                    }
                } else {
                    return null;
                }
            }
        }

        if (this.bloqueElse != null) {
            Entorno entornoIf = new Entorno(e, Entorno.EnumEntorno.IF);
            Object resultElse = this.bloqueElse.Ejecutar(entornoIf);
            if (resultElse != null) {
                if (resultElse instanceof Break) {
                    return resultElse;
                } else if (resultElse instanceof Continue) {
                    return resultElse;
                } else if (resultElse instanceof Return) {
                    return resultElse;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
