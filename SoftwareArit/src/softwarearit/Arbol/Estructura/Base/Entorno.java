/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura.Base;

import java.util.HashMap;

/**
 *
 * @author chicas
 */
public class Entorno {
    
    public Entorno anterior;
    public HashMap<String, Simbolo> tabla;

    /**
     * Entorno anterior para construir otro entorno
     *
     * @param anterior - Entorno
     */
    public Entorno(Entorno anterior) {
        this.anterior = anterior;
    }
    
    public void insertar(String nombre, Simbolo simbolo, int linea, int columna) {
        if (tabla.containsKey(nombre)) {
            softwarearit.Frame.Interfaz.printConsola("No estoy implementado ENTORNO-INSERTAR");
            //softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "La variable ya se encuentra declarada", linea, columna));
        } else {
            tabla.put(nombre, simbolo);
        }
    }
    
}
