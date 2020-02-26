/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;

/**
 *
 * @author chicas
 */
public class Break extends Instruccion{

    public Break(int linea,int columna){
        this.LINEA = linea;
        this.COLUMNA = columna;
    }
    
    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
