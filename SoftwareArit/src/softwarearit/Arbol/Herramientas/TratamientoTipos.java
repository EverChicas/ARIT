/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.Tipo.EnumTipo;
import softwarearit.Arbol.Expresiones.Expresion;

/**
 *
 * @author chicas
 */
public class TratamientoTipos {

    public TratamientoTipos() {

    }

    public EnumTipo tipoSuperior(Expresion var1, Expresion var2) {

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

}
