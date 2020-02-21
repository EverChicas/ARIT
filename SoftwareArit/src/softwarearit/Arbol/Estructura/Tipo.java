/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura;

/**
 *
 * @author chicas
 */
public class Tipo {
    
    /**
     * Tipo de dato que estoy utilizando
     * 
     */
    private EnumTipo Tipo;

    /**
     * Tipo de dato que es
     * @param Tipo - Resive el tipo de dato del simbolo
     */
    public Tipo(EnumTipo Tipo) {
        this.Tipo = Tipo;
    }
    
    
    
    /**
     * EnumTipo contienen los tipos de datos que puedo usar, tanto primitivos como estructuras
     * 
     */
    public enum EnumTipo {
        ENTERO,
        NUMERIC,
        BOOLEAN,
        STRING,
        VECTOR,
        LISTA,
        MATRIZ,
        ARRAY,
        ERROR
    }

}
