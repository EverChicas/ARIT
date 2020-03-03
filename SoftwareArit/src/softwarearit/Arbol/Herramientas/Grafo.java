/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Instrucciones.Instruccion;

/**
 *
 * @author chicas
 */
public class Grafo {

    private int NumeroNodo;

    public Grafo() {
        this.NumeroNodo = 0;
    }

    public void nuevoGrafo() {
        this.NumeroNodo = 0;
    }

    public String getNombreNodo() {
        this.NumeroNodo++;
        return String.valueOf(this.NumeroNodo);
    }

    /**
     *
     * @param exp - Tipo Expresion
     * @return - nodo para el grafico
     */
    public StringBuilder generarHojaExpresion(Expresion exp) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(exp.NOMBRE + " [label = \"" + exp.VALOR.get(0).toString() + "\"];\n");
        return nodo;
    }

    public StringBuilder generarHojaInstruccion(Instruccion ins, String nombre) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(ins.NOMBRE + " [label = \"" + nombre + "\"];\n");
        return nodo;
    }

    /**
     *
     * @param nombreExpresion - nombre de la expresion
     * @param padre - expresion
     * @param hijo - hijos nodos
     * @return StringBuilder con el grafico
     */
    public StringBuilder generarGraficaUnHijo(String nombreExpresion, Nodo padre, Nodo hijo) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(hijo.GRAFICA);
        nodo.append(padre.NOMBRE + " [label = \"" + nombreExpresion + "\"];\n");
        nodo.append(padre.NOMBRE + " -> " + hijo.NOMBRE + ";\n");
        return nodo;
    }

    /**
     *
     * @param nombreExpresion - nombre de la expresion
     * @param padre - nodo de la expresion
     * @param hijo1 - expresion hijo 1
     * @param hijo2 - expresion hijo 2
     * @return
     */
    public StringBuilder generarGraficaExpresionDosHijos(String nombreExpresion, Nodo padre, Nodo hijo1, Nodo hijo2) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(generarGraficaUnHijo(nombreExpresion, padre, hijo1));
        nodo.append(generarGraficaUnHijo(nombreExpresion, padre, hijo2));
        return nodo;
    }

    public StringBuilder generarGraficaExpresionTresHijos(String nombreExpresion, Nodo padre, Nodo hijo1, Nodo hijo2, Nodo hijo3) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(generarGraficaUnHijo(nombreExpresion, padre, hijo1));
        nodo.append(generarGraficaUnHijo(nombreExpresion, padre, hijo2));
        nodo.append(generarGraficaUnHijo(nombreExpresion, padre, hijo3));
        return nodo;
    }

    /**
     * Metodo para graficar la raiz el programa
     *
     * @param lista
     * @return
     */
    public StringBuilder generarGraficaRoot(LinkedList<Instruccion> lista) {
        StringBuilder nodo = new StringBuilder();
        String root = getNombreNodo();

        nodo.append(root + " [label = \"root\"];\n");

        for (Instruccion ins : lista) {
            nodo.append(ins.GRAFICA);
        }

        for (Instruccion ins : lista) {
            nodo.append(root + " -> " + ins.NOMBRE + ";\n");
        }

        return nodo;
    }

    /**
     * Metodo para generar grafica, cuando el padre tiene varios hijos en una
     * lista
     *
     * @param nombreEtiqueta - nombre de la funcion, padre de instrucciones
     * @param padre - nodo padre con su nombre
     * @param lista - lista de instrucciones hijas
     * @return - StringBuilder con el codigo para el grafico
     */
    public StringBuilder generarGraficaPadreHijos(String nombreEtiqueta, Nodo padre, LinkedList<Instruccion> lista) {
        StringBuilder nodo = new StringBuilder();

        nodo.append(padre.NOMBRE + "[label = \"" + nombreEtiqueta + "\"];\n");

        for (Instruccion ins : lista) {
            nodo.append(ins.GRAFICA);
        }

        for (Instruccion ins : lista) {
            nodo.append(padre.NOMBRE + " -> " + ins.NOMBRE + ";\n");
        }

        return nodo;
    }

//    public StringBuilder generarGraficaElseIf(String nombreEtiqueta, Nodo padre, Nodo condicion, LinkedList<Instruccion> lista) {
//        StringBuilder nodo = new StringBuilder();
//
//        nodo.append(padre.NOMBRE + "[label = \"" + nombreEtiqueta + "\"];\n");
//
//        for (Instruccion ins : lista) {
//            nodo.append(ins.GRAFICA);
//        }
//
//        for (Instruccion ins : lista) {
//            nodo.append(padre.NOMBRE + " -> " + ins.NOMBRE + ";\n");
//        }
//
//        return nodo;
//    }

}
