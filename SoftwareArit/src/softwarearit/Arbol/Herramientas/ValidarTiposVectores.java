/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class ValidarTiposVectores {

    public static boolean validarVectoresRelacionales(int linea, int columna, Expresion resul1, Expresion resul2) {
        if ((resul1.TIPO.Tipo == Tipo.EnumTipo.ENTERO || resul1.TIPO.Tipo == Tipo.EnumTipo.NUMERIC)
                && (resul2.TIPO.Tipo == Tipo.EnumTipo.ENTERO || resul2.TIPO.Tipo == Tipo.EnumTipo.NUMERIC)) {
            return true;
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipos " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, linea, columna));
            return false;
        }
    }

    public static boolean validarVectorAritmeticoMDP(int linea, int columna, Expresion resul1, Expresion resul2) {
        switch (TratamientoTipos.tipoSuperiorExpresion(resul1, resul2)) {
            case ERROR:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, linea, columna));
                return false;
            case STRING:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, linea, columna));
                return false;
            default:
                return true;
        }
    }

    public static boolean validarVectorAritmeticoS(int linea, int columna, Expresion resul1, Expresion resul2) {
        switch (TratamientoTipos.tipoSuperiorExpresion(resul1, resul2)) {
            case ERROR:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, linea, columna));
                return false;
        }
        return true;
    }

    public static boolean validarVectorAritmeticoNegativo(Expresion resul1) {
        switch (TratamientoTipos.tipoSuperiorExpresion(resul1, resul1)) {
            case ERROR:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo, resul1.LINEA, resul1.COLUMNA));
                return false;
            case STRING:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo, resul1.LINEA, resul1.COLUMNA));
                return false;
        }
        return true;
    }

}
