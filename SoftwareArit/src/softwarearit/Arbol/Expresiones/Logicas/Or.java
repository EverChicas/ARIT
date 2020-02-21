/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Logicas;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Expresiones.Expresion;

/**
 *
 * @author chicas
 */
public class Or extends Expresion{

    public Or(int linea,int columna,Expresion valor){
        this.Linea = linea;
        this.Columna = columna;
        this.Valor = valor;
    }
    
    @Override
    public Expresion getValor(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
