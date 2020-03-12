/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.Tipo.EnumTipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;

/**
 *
 * @author chicas
 */
public class TratamientoTipos {

    public static EnumTipo tipoSuperiorExpresion(Expresion var1, Expresion var2) {

        if (var1.TIPO.Tipo == Tipo.EnumTipo.STRING || var2.TIPO.Tipo == Tipo.EnumTipo.STRING) {
            return Tipo.EnumTipo.STRING;
        } else if (var1.TIPO.Tipo == Tipo.EnumTipo.ENTERO && var2.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
            return Tipo.EnumTipo.ENTERO;
            //caso especial cuando viene numeric y boolean
        } else if (var1.TIPO.Tipo == Tipo.EnumTipo.NUMERIC) {
            if (var2.TIPO.Tipo == Tipo.EnumTipo.NUMERIC || var2.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
                return Tipo.EnumTipo.NUMERIC;
            } else {
                return Tipo.EnumTipo.ERROR;
            }
        } else if (var2.TIPO.Tipo == Tipo.EnumTipo.NUMERIC) {
            if (var1.TIPO.Tipo == Tipo.EnumTipo.NUMERIC || var1.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
                return Tipo.EnumTipo.NUMERIC;
            } else {
                return Tipo.EnumTipo.ERROR;
            }
        } else {
            return Tipo.EnumTipo.ERROR;
        }
    }

    public static EnumTipo TipoSuperior(Expresion var1, Expresion var2) {

        if (var1.TIPO.Tipo == Tipo.EnumTipo.LISTA || var2.TIPO.Tipo == Tipo.EnumTipo.LISTA) {
            return Tipo.EnumTipo.LISTA;
        } else if (var1.TIPO.Tipo == Tipo.EnumTipo.STRING || var2.TIPO.Tipo == Tipo.EnumTipo.STRING) {
            return Tipo.EnumTipo.STRING;
        } else if (var1.TIPO.Tipo == Tipo.EnumTipo.NUMERIC || var2.TIPO.Tipo == Tipo.EnumTipo.NUMERIC) {
            return Tipo.EnumTipo.NUMERIC;
        } else if (var1.TIPO.Tipo == Tipo.EnumTipo.ENTERO || var2.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
            return Tipo.EnumTipo.ENTERO;
        } else if (var1.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN || var2.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
            return Tipo.EnumTipo.BOOLEAN;
        } else {
            return Tipo.EnumTipo.ERROR;
        }
    }

    public static EnumTipo tipoSuperiorLista(ArrayList<Object> lista) {
        EnumTipo tipoSuperior = Tipo.EnumTipo.ERROR;

        if (lista.get(0) instanceof Valor) {
            Valor actual = (Valor) lista.get(0);
            tipoSuperior = actual.TIPO.Tipo;

            for (Object item : lista) {
                if (item instanceof Valor) {
                    if (((Valor) ((Expresion) item)).TIPO.Tipo != Tipo.EnumTipo.NULL) {
                        tipoSuperior = TipoSuperior(actual, (Valor) item);
                        actual.TIPO.Tipo = tipoSuperior;
                    }
                }
            }
        }
        return tipoSuperior;
    }

}
