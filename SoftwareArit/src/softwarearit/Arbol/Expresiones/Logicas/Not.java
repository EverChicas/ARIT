/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Logicas;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Expresiones.Expresion;

/**
 *
 * @author chicas
 */
public class Not extends Expresion{
    
    Expresion var1;
    
    public Not(int linea,int columna,Expresion valor){
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = valor;
    }

    @Override
    public Expresion getValor(Entorno e) {
        // TODO HACER IMPLEMENTACION
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
