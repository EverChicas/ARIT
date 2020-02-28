/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;

/**
 *
 * @author chicas
 */
public class Bloque extends Instruccion{

    LinkedList<Instruccion> instrucciones;
    
    public Bloque(LinkedList<Instruccion> instrucciones){
        this.instrucciones = instrucciones;
        
       
    }
    
    // TODO al hacer la grafica de los bloques tengo que mandar el padre del bloque y no el bloque como padre    
    
    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
