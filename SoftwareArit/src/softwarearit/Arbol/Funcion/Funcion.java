/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Instrucciones.Instruccion;

/**
 *
 * @author chicas
 */
public class Funcion extends AbstractFuncion {

    String nombre;
    LinkedList<Nodo> parametros;
    Instruccion instrucciones;

    /**
     *
     * @param nombre
     * @param parametros
     * @param instrucciones
     */
    public Funcion(String nombre, LinkedList<Nodo> parametros, Instruccion instrucciones) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

    public Funcion(String nombre, LinkedList<Nodo> parametros) {

    }

    public Funcion(String nombre, Instruccion instrucciones) {

    }

    @Override
    public Expresion getValor(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
