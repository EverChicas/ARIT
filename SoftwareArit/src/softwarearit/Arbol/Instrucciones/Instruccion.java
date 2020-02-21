/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;

/**
 *
 * @author chicas
 */
public abstract class Instruccion extends Nodo{
    
    public abstract Object Ejecutar(Entorno e);
}
