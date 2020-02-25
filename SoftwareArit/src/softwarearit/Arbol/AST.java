package softwarearit.Arbol;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Instrucciones.Instruccion;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chicas
 */
public class AST {
    public LinkedList<Instruccion> INSTRUCCIONES;
    public Entorno TABLA;
    public static int i;

    /**
     * Constructor del arbol AST
     * 
     * @param - LISTA_INSTRUCCIONES lista de instrucciones que tienen 
     */
    public AST(LinkedList<Instruccion> instrucciones) {
        this.INSTRUCCIONES = instrucciones;
        this.TABLA = new Entorno(null);
    }
    
    /**
     * Retorna un valor de ser necesario
     * 
     * @return
     */
    public Object ejecutar(){
        
        for(Instruccion instruccion : INSTRUCCIONES){
            instruccion.Ejecutar(TABLA);
        }
        return null;
    }
    
    
    
}
