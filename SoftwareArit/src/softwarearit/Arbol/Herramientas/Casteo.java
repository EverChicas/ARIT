/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;

/**
 *
 * @author chicas
 */
public class Casteo {

    public static ArrayList<Object> CasteoDeArray(ArrayList<Object> lista, Tipo.EnumTipo tipo) {
        ArrayList<Object> retornarValores = new ArrayList<>();

        for (Object nodo : lista) {
            if (nodo instanceof Valor) {
                if (((Valor) nodo).TIPO.Tipo == tipo) {
                    retornarValores.add(new Valor(new Tipo(tipo), ((Valor) nodo).VALOR));
                } else {
                    if (tipo == Tipo.EnumTipo.STRING) {
                        retornarValores.add(toString((Expresion) nodo));
                    } else if (tipo == Tipo.EnumTipo.NUMERIC) {
                        retornarValores.add(toDouble((Expresion) nodo));
                    } else if (tipo == Tipo.EnumTipo.ENTERO) {
                        retornarValores.add(toEntero((Expresion) nodo));
                    }
                }
            }
        }

        return retornarValores;
    }

    private static Valor toString(Expresion valor) {
        return new Valor(new Tipo(Tipo.EnumTipo.STRING), String.valueOf(valor.VALOR.get(0)));
    }

    private static Valor toDouble(Expresion valor) {
        if (valor.TIPO.Tipo == Tipo.EnumTipo.STRING || valor.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
            return new Valor(new Tipo(Tipo.EnumTipo.NUMERIC), Double.parseDouble(valor.VALOR.get(0).toString()));
        } else if (valor.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
            if (Boolean.parseBoolean(valor.VALOR.get(0).toString())) {
                return new Valor(new Tipo(Tipo.EnumTipo.NUMERIC), 1.0);
            } else {
                return new Valor(new Tipo(Tipo.EnumTipo.NUMERIC), 0.0);
            }
        }
        return (Valor) valor;
    }

    private static Valor toEntero(Expresion valor) {
        if (Boolean.parseBoolean(valor.VALOR.get(0).toString())) {
            return new Valor(new Tipo(Tipo.EnumTipo.ENTERO), 1.0);
        } else {
            return new Valor(new Tipo(Tipo.EnumTipo.ENTERO), 0.0);
        }
    }
}
