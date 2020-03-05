/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura;

import java.util.HashMap;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Entorno {

    /**
     * Estoy pensando en poner una variable que me diga en que entorno me
     * encuentro, para el entorno global, este se llamaria global, igual que
     * para manejar entornos de funciones, if, switch, for, tambien deberia de
     * poner un enum que tenga estos tipos de valores
     */
    public Entorno anterior;
    public HashMap<String, Simbolo> tabla;

    /**
     * Entorno anterior para construir otro entorno
     *
     * @param anterior - Entorno
     */
    public Entorno(Entorno anterior, EnumEntorno tipoEntorno) {
        this.anterior = anterior;
    }

    public void insertar(String nombre, Simbolo simbolo, int linea, int columna) {
        if (tabla.containsKey(nombre)) {
            Interfaz.printConsola("No estoy implementado ENTORNO-INSERTAR");
            //softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "La variable ya se encuentra declarada", linea, columna));
        } else {
            tabla.put(nombre, simbolo);
        }
    }

    public enum EnumEntorno {
        GLOBAL,
        IF,
        FOR,
        SWITCH,
        WHILE,
    }

}
