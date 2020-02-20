/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura.Base;

/**
 *
 * @author chicas
 */
public abstract class Instruccion extends Nodo{
    
    /**
     * Ejecutar de cada instruccion
     * 
     * @param e - Resive entorno local de la instruccion Entorno
     * @return - Retorna un objecto Object
     */
    public abstract Object ejecutar(Entorno e);
    
}
