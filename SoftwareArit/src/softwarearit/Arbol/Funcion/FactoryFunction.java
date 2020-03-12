/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Instrucciones.Instruccion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class FactoryFunction {

    public static AbstractFuncion getFunctionEstructura(String tipo, LinkedList<Nodo> parametros) {
        switch (tipo.toLowerCase()) {
            case "c":
                return new C(parametros);
            case "list":
                return new List(parametros);
            default:
                return new Funcion(tipo, parametros);
        }
    }

}