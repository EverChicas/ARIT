/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Nodo;

/**
 *
 * @author chicas
 */
public class FactoryFunction {

    public static AbstractFuncion getLlamadaFuncion(int linea, int columna, String identificador) {
        return llamadaFuncion(linea, columna, identificador, null);
    }

    /**
     * Llamada a funcion con parametros de entrada
     *
     * @param identificador
     * @param parametros
     * @return
     */
    public static AbstractFuncion getLlamadaFuncion(int linea, int columna, String identificador, LinkedList<Nodo> parametros) {
        return llamadaFuncion(linea, columna, identificador, parametros);
    }

    private static AbstractFuncion llamadaFuncion(int linea, int columna, String identificador, LinkedList<Nodo> parametros) {
        switch (identificador.toLowerCase()) {
            case "c":
                return new C(linea, columna, parametros);
            case "list":
                return new List(linea, columna, parametros);
            default:
                if (parametros == null) {
                    return new LlamadaFuncion(linea, columna, identificador);
                } else {
                    return new LlamadaFuncion(linea, columna, identificador, parametros);
                }
        }
    }

}
