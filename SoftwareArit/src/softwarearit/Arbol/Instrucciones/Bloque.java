/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Bloque extends Instruccion {

    public LinkedList<Nodo> instrucciones;

    public Bloque(LinkedList<Nodo> instrucciones) {
        this.instrucciones = instrucciones;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("instrucciones", this, instrucciones);
    }

    // TODO al hacer la grafica de los bloques tengo que mandar el padre del bloque y no el bloque como padre    
    @Override
    public Object Ejecutar(Entorno e) {
        for (Nodo nodo : this.instrucciones) {

            if (nodo instanceof Instruccion) {
                Object resultBloque = ((Instruccion) nodo).Ejecutar(e);

                if (resultBloque != null) {
                    if (resultBloque instanceof Break) {
                        return resultBloque;
                    } else if (resultBloque instanceof Continue) {
                        return resultBloque;
                    } else if (resultBloque instanceof Return) {
                        return resultBloque;
                    } else {
                        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en bloque", LINEA, COLUMNA));
                    }
                }

            } else if (nodo instanceof Expresion) {
                ((Expresion) nodo).getValor(e);
            }

        }
        return null;
    }

}
