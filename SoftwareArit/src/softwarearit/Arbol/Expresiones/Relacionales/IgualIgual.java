/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Relacionales;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Expresiones.Expresion;

/**
 *
 * @author chicas
 */
public class IgualIgual extends Expresion {
    
    Expresion var1;
    Expresion var2;
    
    public IgualIgual(int linea,int columna,Expresion var1,Expresion var2){
        this.Linea = linea;
        this.Columna = columna;
        this.var1 = var1;
        this.var2 = var2;
    }

    @Override
    public Expresion getValor(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
