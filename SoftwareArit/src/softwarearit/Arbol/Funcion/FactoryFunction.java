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

    public static AbstractFuncion getLlamadaFuncion(String identificador) {
        return llamadaFuncion(identificador, null);
    }

    /**
     * Llamada a funcion con parametros de entrada
     * 
     * @param identificador
     * @param parametros
     * @return
     */
    public static AbstractFuncion getLlamadaFuncion(String identificador, LinkedList<Nodo> parametros) {
        return llamadaFuncion(identificador, parametros);
    }

    private static AbstractFuncion llamadaFuncion(String identificador, LinkedList<Nodo> parametros) {
        switch (identificador.toLowerCase()) {
            case "c":
                return new C(parametros);
            case "list":
                return new List(parametros);
            default:
                if (parametros == null) {
                    return new LlamadaFuncion(identificador);
                } else {
                    return new LlamadaFuncion(identificador, parametros);
                }
        }
    }

}
