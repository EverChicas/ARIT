/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura;

import java.util.HashMap;
import softwarearit.Arbol.Expresiones.Expresion;
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
    public EnumEntorno tipoEntorno;

    /**
     * Entorno anterior para construir otro entorno
     *
     * @param anterior - Entorno
     */
    public Entorno(Entorno anterior, EnumEntorno tipoEntorno) {
        this.tabla = new HashMap<>();
        this.anterior = anterior;
        this.tipoEntorno = tipoEntorno;
    }

    public void insertar(String nombre, Simbolo simbolo, int linea, int columna) {
        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.tabla.containsKey(nombre.toLowerCase())) {
                if (e.tipoEntorno == EnumEntorno.GLOBAL || e.tipoEntorno == EnumEntorno.FUNCION) {
                    if (simbolo.Tipo.Tipo != Tipo.EnumTipo.ERROR) {
                        e.tabla.put(nombre.toLowerCase(), simbolo);
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
        if (simbolo.Tipo.Tipo != Tipo.EnumTipo.ERROR) {
            tabla.put(nombre.toLowerCase(), simbolo);
            return;
        } else {
            return;
        }
    }

    public Simbolo buscar(String identificador) {
        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.tabla.containsKey(identificador.toLowerCase())) {
                return e.tabla.get(identificador.toLowerCase());
            }
        }
        return null;
    }

    public enum EnumEntorno {
        GLOBAL,
        IF,
        FOR,
        SWITCH,
        WHILE,
        FUNCION,
        DO,
    }

}
