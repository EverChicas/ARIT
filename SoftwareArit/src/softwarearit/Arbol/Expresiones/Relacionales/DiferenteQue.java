/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Relacionales;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class DiferenteQue extends Expresion {

    Expresion var1;
    Expresion var2;

    public DiferenteQue(int linea, int columna, Expresion var1, Expresion var2) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = var1;
        this.var2 = var2;
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        Expresion resul1 = this.var1.getValor(e);
        Expresion resul2 = this.var2.getValor(e);

        if (resul1.TIPO.Tipo == Tipo.EnumTipo.STRING && resul2.TIPO.Tipo == Tipo.EnumTipo.STRING) {
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear();
            resul.VALOR.add(SonDiferentes(resul1.VALOR.get(0).toString(), resul2.VALOR.get(0).toString()));
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN && resul2.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear();
            resul.VALOR.add(SonDiferentes(resul1.VALOR.get(0).toString(), resul2.VALOR.get(0).toString()));
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.ENTERO || resul1.TIPO.Tipo == Tipo.EnumTipo.NUMERIC
                && resul2.TIPO.Tipo == Tipo.EnumTipo.ENTERO || resul2.TIPO.Tipo == Tipo.EnumTipo.NUMERIC) {
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear();
            resul.VALOR.add(SonDiferentes(resul1.VALOR.get(0).toString(), resul2.VALOR.get(0).toString()));
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipos para igualigual " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, LINEA, COLUMNA));
        }
        return resul;
    }

    private boolean SonDiferentes(String valor1, String valor2) {
        if (valor1.equals(valor2)) {
            return false;
        } else {
            return true;
        }
    }

}
