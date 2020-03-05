/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura;

import java.util.ArrayList;

/**
 *
 * @author chicas
 */
public class Simbolo {

    public Tipo Tipo;
    public String Id;
    public ArrayList<Object> Valor;

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
        this.Valor = new ArrayList<>();
        this.Valor.add(Valor);
    }

    /**
     *
     * @param Tipo
     * @param Id
     * @param Valor - array de valores
     */
    public Simbolo(Tipo Tipo, String Id, ArrayList<Object> Valor) {
        this.Tipo = Tipo;
        this.Id = Id;
        this.Valor = Valor;
    }

}
