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
public class Simbolo {
    public Tipo Tipo;
    public String Id;
    public Object Valor;

    /**
     * Nuevo simbolo reconocido
     * 
     * @param Tipo - Tipo
     * @param Id - String
     * @param Valor - Object
     * 
     */
    public Simbolo(Tipo Tipo, String Id, Object Valor) {
        this.Tipo = Tipo;
        this.Id = Id;
        this.Valor = Valor;
    }
    
}
