/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;

/**
 *
 * @author chicas
 */
public class Valor extends Expresion{

    /**Toma los valores que usa la expresion y de este nivel
     * tambien se puede pasar los que usa nodo
     * por ser extends de esta
     * 
     * @param tipo
     * @param valor 
     */
    public Valor(Tipo tipo,Object valor) {
        this.Tipo = tipo;
        this.Valor = valor;
    }
    
    public Valor(Tipo tipo,Object valor,int nodo){
        this.Tipo = tipo;
        this.Valor = valor;
        this.Grafica = valor.toString();
    }
    
    
    @Override
    public Expresion getValor(Entorno e) {
        return new Valor(Tipo, Valor);
    }
    
}
