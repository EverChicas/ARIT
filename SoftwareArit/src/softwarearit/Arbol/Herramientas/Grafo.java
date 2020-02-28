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
    public StringBuilder generarHoja(Expresion exp) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(exp.NOMBRE + " [label = \"" + exp.VALOR.get(0).toString() + "\"];\n");
        return nodo;
    }

    /**
     *
     * @param nombreExpresion - nombre de la expresion
     * @param padre - expresion
     * @param hijo - hijos nodos
     * @return StringBuilder con el grafico
     */
    public StringBuilder generarGraficaExpresion(String nombreExpresion, Nodo padre, Expresion hijo) {
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
    public StringBuilder generarGraficaExpresionDosHijos(String nombreExpresion, Nodo padre, Expresion hijo1, Expresion hijo2) {
        StringBuilder nodo = new StringBuilder();
        nodo.append(generarGraficaExpresion(nombreExpresion, padre, hijo1));
        nodo.append(generarGraficaExpresion(nombreExpresion, padre, hijo2));
        return nodo;
    }
    
    public StringBuilder generarGraficaRoot(LinkedList<Instruccion> lista){
        StringBuilder nodo = new StringBuilder();
        String root = getNombreNodo();
        
        nodo.append(root + " [label = \"root\"];\n");
        
        for(Instruccion ins:lista){
            nodo.append(ins.GRAFICA);
        }
        
        for(Instruccion ins:lista){
            nodo.append(root + " -> " + ins.NOMBRE + ";\n");
        }
        
        return nodo;
    }
    
}
