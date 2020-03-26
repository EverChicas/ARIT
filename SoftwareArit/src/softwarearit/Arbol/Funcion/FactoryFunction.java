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
            case "matrix":
                return new Matrix(linea, columna, parametros);
            case "typeof":
                return new TypeOf(linea, columna, parametros);
            case "length":
                return new Length(linea, columna, parametros);
            // TODO MATRIX
            case "ncol":
            case "nrow":

            case "stringlength":
                return new StringLength(linea, columna, parametros);
            case "remove":
                return new Remove(linea, columna, parametros);
            case "tolowercase":
                return new ToLowerCase(linea, columna, parametros);
            case "touppercase":
                return new ToUpperCase(linea, columna, parametros);
            case "trunk":
                return new Trunk(linea, columna, parametros);
            case "round":
                return new Round(linea, columna, parametros);
            case "mean":
                return new Mean(linea, columna, parametros);
            case "median":
                return new Median(linea, columna, parametros);
            case "mode":
                return new Mode(linea, columna, parametros);
            case "pie":
            case "barplot":
            case "plot":
            case "hist":
            default:
                if (parametros == null) {
                    return new LlamadaFuncion(linea, columna, identificador);
                } else {
                    return new LlamadaFuncion(linea, columna, identificador, parametros);
                }
        }
    }

}
