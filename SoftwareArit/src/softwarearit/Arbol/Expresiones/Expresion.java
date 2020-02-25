/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones;

import java.util.ArrayList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.Tipo;

/**
 *
 * @author chicas
 * 
 * Expresion usada para guardar el valor de una expresion
 * su tipo, extiende de nodo, para poder usar lo que es linea, columna,grafica
 */
public abstract class Expresion extends Nodo{
    public Tipo Tipo;
    public ArrayList<Object> Valor;
    
    public abstract Expresion getValor(Entorno e);
    
}
