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
public class TipoError {
    
    public EnumTipoError TipoError;

    /**
     * Contructor de tipo de error
     *
     * @param TipoError - EnumTipoError error reconocido en la entrada
     */
    public TipoError(EnumTipoError TipoError) {
        this.TipoError = TipoError;
    }
    
    public enum EnumTipoError{
        LEXICO,
        SINTACTICO,
        SEMANTICO
    }
    
}
